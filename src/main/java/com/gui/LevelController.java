package com.gui;

import com.game.*;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LevelController {
    @FXML
    private ImageView result, playerCar, computerCar;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label wordLabel;
    @FXML
    private VBox endBox;
    @FXML
    private HBox hBox;
    private Stage stage;
    private Parent root;
    private ArrayList<String> words = new ArrayList<>();
    private int lives = 3;

    private int wordAmount;

    @FXML
    void animationPop(MouseEvent event) {
        ((Node)event.getSource()).setStyle("-fx-effect: dropShadow(gaussian, " + "#E34255" + ", 28, 0.7, 0, 0)");
    }
    @FXML
    void animationPopUp(MouseEvent event) {
        ((Node)event.getSource()).setStyle(null);
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        Music.playButtonSound();
        Music.changeSong(Music.getStartScreenSong());
        StartScreenController.sceneGenerator(stage,root,"LevelsScreen-view.fxml", event, "Levels Screen");
    }

    @FXML
    void initialize() {
        Music.changeSong(Music.getGameScreenSong());
        assert borderPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'Level-view.fxml'.";
        assert computerCar != null : "fx:id=\"computerCar\" was not injected: check your FXML file 'Level-view.fxml'.";
        assert endBox != null : "fx:id=\"endBox\" was not injected: check your FXML file 'Level-view.fxml'.";
        assert hBox != null : "fx:id=\"hBox\" was not injected: check your FXML file 'Level-view.fxml'.";
        assert playerCar != null : "fx:id=\"playerCar\" was not injected: check your FXML file 'Level-view.fxml'.";
        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'Level-view.fxml'.";
        assert wordLabel != null : "fx:id=\"wordLabel\" was not injected: check your FXML file 'Level-view.fxml'.";

        borderPane.getStyleClass().add("firstMap");

        words.addAll(LevelManager.getCurrentLevel().getWordsList());
        wordAmount = words.size();
        setCarImages();
        wordLabel.setText(words.get(0));


     }

    public void setCarImages(){
        playerCar.setImage(new Image(Objects.requireNonNull(StartScreen.class.getResourceAsStream(LoginManager.getCurrentPlayer().getSelectedCar().getImageUrl()))));

        int levelNum = LevelManager.getCurrentLevel().getLevelNum();
        computerCar.setImage(new Image(Objects.requireNonNull(StartScreen.class.getResourceAsStream("/assets/vehicles/car"+(int)Math.ceil(levelNum/4.0)+".png"))));
    }

    double X = 0;
    double Y = 0;

    @FXML
    void checkText(KeyEvent event) {
        if((event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER) && !endBox.isVisible()){
            wordLabel.setStyle("-fx-text-fill: black");
            TextField field = (TextField) event.getSource();
            //Checks if the typed word was correct
            if(field.getText().trim().equals(wordLabel.getText())){
                playerCar.setVisible( true );

                TranslateTransition translate = new TranslateTransition();

                if (X == 0) {
                    translate.setToX(X - (360.0 / (wordAmount * (wordAmount / 3.0))));
                    X = -(360.0 / (wordAmount * (wordAmount / 3.0)));
                }
                else {
                    translate.setToX(X + X);
                    X = X + X;
                }

                if (Y == 0) {
                    translate.setToY(Y - 270.0 / (wordAmount * (wordAmount / 3.0)));
                    Y = -270.0 / (wordAmount * (wordAmount / 3.0));
                }
                else
                {
                    translate.setToY(Y + (Y + 60.0 / (wordAmount * (wordAmount / 3.0))));
                    Y = Y + Y + 60.0 / (wordAmount * (wordAmount / 3.0));
                }

                System.out.println(" " + playerCar.getX() + playerCar.getY() + playerCar.getLayoutX() + playerCar.getLayoutY());

                translate.setDuration(Duration.millis(1000));
                translate.setAutoReverse(true);
                translate.setNode(playerCar);
                translate.play();



                words.remove(0);
                //Checks if there are no words left.
                if(words.isEmpty()){
                    Player currentPlayer = LoginManager.getCurrentPlayer();
                    Level currentLevel = LevelManager.getCurrentLevel();

                    //Increases players score.
                    int playerScore = currentPlayer.getScore(currentLevel);
                    LoginManager.getCurrentPlayer().setLevelAndScore(currentLevel, playerScore+1);

                    //Adds new level and car to the player.
                    Level nextLevel = LevelManager.getLevel(LevelManager.getCurrentLevel().getLevelNum()+1);
                    if(!currentPlayer.getScoreMap().containsKey(nextLevel)){
                        currentPlayer.setLevelAndScore(nextLevel, 0);
                        int newCar = (int)Math.ceil((nextLevel.getLevelNum())/5.0);
                        if(currentLevel.getLevelNum()%5==0)
                            currentPlayer.getGarage().add(new Vehicle("Car"+newCar, "/assets/vehicles/car"+newCar+".png"));
                    }

                    //Makes visible the Victory pane.
                    endBox.setVisible(true);
                    field.setEditable(false);
                } else {
                    wordLabel.setText(words.get(0));
                    field.setText("");
                }
            } else {
                wordLabel.setStyle("-fx-text-fill: red");
                field.setText("");

                //Removes a heart icon
                lives--;
                hBox.getChildren().get(lives).setVisible(false);

                //If there are no hearts left makes visible the Losing pane.
                if(lives==0){
                    field.setEditable(false);
                    endBox.setVisible(true);
                    result.setImage(new Image(Objects.requireNonNull(StartScreen.class.getResourceAsStream("/assets/scene Elements/youLoseTitle.png"))));
                }
            }
        }
    }
}

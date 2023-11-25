package com.gui;

import com.game.LevelManager;
import com.game.LoginManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class StartScreenController {
    private Stage stage;
    private Parent root;
    @FXML
    private ImageView loginButton, signUpButton, quitButton,  leaderboardBtn;

    @FXML
    void initialize() {
        assert leaderboardBtn != null : "fx:id=\"leaderboardBtn\" was not injected: check your FXML file 'StartScreen-view.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'StartScreen-view.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'StartScreen-view.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'StartScreen-view.fxml'.";

        LevelManager.initializeLevels();
    }

    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        if (event.getSource() == loginButton)
            sceneGenerator(stage, root, "LoginScreen-view.fxml", event, "Login Screen");
        else if (event.getSource() == signUpButton)
            sceneGenerator(stage, root,"SignUpScreen-view.fxml", event, "SignUp Screen");
        else if (event.getSource() == leaderboardBtn)
            sceneGenerator(stage,root,"LeaderBoardScreen-view.fxml", event, "LeaderBoard Screen");
        else if (event.getSource() == quitButton){
            LoginManager.savePlayerDatabase("players.txt");
            System.exit(0);
        }
    }

    public static void sceneGenerator(Stage stage, Parent root, String name, MouseEvent event, String Player_Screen) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(StartScreenController.class.getResource(name)));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.setFullScreen(false);
        stage.setResizable(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle(Player_Screen);
        stage.show();
    }

}

package com.gui;

import com.game.LoginManager;
import com.game.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SignUpScreenController {
    @FXML
    private TextField userNameTextField, phoneNumberField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView goBackButton, loginButton;
    @FXML
    private ImageView greenIcon, redIcon, blueIcon, purpleIcon;
    private final ArrayList<ImageView> iconList = new ArrayList<>();
    private String profileImage;
    private Stage stage;
    private Parent root;

    @FXML
    void returnToMain(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartScreen-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        configureStage(stage, "Start");
    }

    private void configureStage(Stage stage, String stageTitle) {
        stage.getScene().setRoot(root);
        stage.setFullScreen(false);
        stage.setResizable(true);
        stage.setTitle(stageTitle + " " + "Screen");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }

    @FXML
    void chooseImage(MouseEvent event) {
        ImageView selectedImage = (ImageView) event.getSource();
        for(ImageView image : iconList){
            if(image!=selectedImage){
                image.setStyle(null);
            }
        }
        selectedImage.setStyle("-fx-effect: dropShadow(three-pass-box, " + getIconColour(selectedImage) + ", 30, 0.5, 0, 0)");
        profileImage = selectedImage.getImage().getUrl();

    }

    public String getIconColour(ImageView image){
        if (image.equals(redIcon)) {
            return "red";
        } else if (image.equals(blueIcon)) {
            return "blue";
        } else if (image.equals(greenIcon)) {
            return "green";
        } else if (image.equals(purpleIcon)) {
            return "purple";
        }

        return "black";
    }

    @FXML
    void initialize() {
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert phoneNumberField != null : "fx:id=\"phoneNumberField\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert blueIcon != null : "fx:id=\"blueIcon\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert redIcon != null : "fx:id=\"blueIcon\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert greenIcon != null : "fx:id=\"blueIcon\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        assert purpleIcon != null : "fx:id=\"blueIcon\" was not injected: check your FXML file 'SignUpScreen-view.fxml'.";
        iconList.add(redIcon);
        iconList.add(blueIcon);
        iconList.add(greenIcon);
        iconList.add(purpleIcon);

    }

    public void signUpClicked(MouseEvent event) throws IOException {
        String name = userNameTextField.getText();
        String password = passwordField.getText();
        String phoneNumber = phoneNumberField.getText();

        if(name!=null && password!=null && phoneNumber!=null && profileImage!=null){
            LoginManager loginManager = new LoginManager("players.txt");
            if(!loginManager.playerExists(name)){
                loginManager.addUser(new Player(name, password, phoneNumber, profileImage));
                loginManager.savePlayerDatabase("players.txt");

                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen-view.fxml")));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                configureStage(stage, "Main");
            }
        }
    }
}

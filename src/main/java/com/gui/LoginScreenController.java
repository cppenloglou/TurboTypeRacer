package com.gui;


import com.game.LoginManager;
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
import java.util.Objects;

public class LoginScreenController {
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView goBackButton, loginButton;
    private Stage stage;
    private Parent root;

    @FXML
    public void returnToMain(MouseEvent event) throws IOException {
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
    void initialize() {
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
        assert passwordField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
    }

    public void editProfile(MouseEvent event) throws IOException {
        String name = userNameTextField.getText();
        String password = passwordField.getText();

        if(name!=null && password !=null){
            LoginManager loginManager = new LoginManager("players.txt");

            if(loginManager.isValidLogin(name,password)){
                loginManager.addCurrentPlayer(loginManager.getPlayer(name,password));
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditProfileScreen-view.fxml")));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                configureStage(stage, "EditProfile");
            }
        }
    }

    public void loginClicked(MouseEvent event) throws IOException {
        String name = userNameTextField.getText();
        String password = passwordField.getText();

        if(name!=null && password !=null){
            LoginManager loginManager = new LoginManager("players.txt");

            if(loginManager.isValidLogin(name,password)){
                loginManager.addCurrentPlayer(loginManager.getPlayer(name,password));
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen-view.fxml")));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                configureStage(stage, "MainScreen");
            }
        }
    }
}

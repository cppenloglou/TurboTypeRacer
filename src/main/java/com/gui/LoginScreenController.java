package com.gui;


import com.game.LoginManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
        StartScreenController.sceneGenerator(stage, root, "StartScreen-view.fxml", event, "Start Screen");
    }

    @FXML
    void initialize() {
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
        assert passwordField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'LoginScreen-view.fxml'.";
    }

    //Loads the EditProfileScreen.
    public void editProfile(MouseEvent event) throws IOException {
        String name = userNameTextField.getText();
        String password = passwordField.getText();

        if(name!=null && password !=null){
            if(LoginManager.isValidLogin(name,password)){
                LoginManager.addCurrentPlayer(LoginManager.getPlayer(name));
                StartScreenController.sceneGenerator(stage, root, "EditProfileScreen-view.fxml", event, "Edit Profile Screen");
            }
        }
    }

    //Loads the MainScreen.
    public void loginClicked(MouseEvent event) throws IOException {
        String name = userNameTextField.getText();
        String password = passwordField.getText();

        if(name!=null && password !=null){
            if(LoginManager.isValidLogin(name,password)){
                LoginManager.addCurrentPlayer(LoginManager.getPlayer(name));
                StartScreenController.sceneGenerator(stage, root, "MainScreen-view.fxml", event, "Main Screen");
            }
        }
    }
}

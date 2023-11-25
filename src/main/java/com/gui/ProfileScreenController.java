package com.gui;

import java.io.IOException;

import com.game.Level;
import com.game.LoginManager;
import com.game.Player;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProfileScreenController {

    @FXML
    private ImageView profileImage, goBackButton, updateBtn;

    @FXML
    private TextField userNameTextField, phoneNumberField, passwordField;

    @FXML
    private ListView<String> statsList;

    private Stage stage;
    private Parent root;


    @FXML
    void returnToMain(MouseEvent event) throws IOException {
        StartScreenController.sceneGenerator(stage, root, "MainScreen-view.fxml", event, "Main Screen");

    }

    @FXML
    void updateClicked(MouseEvent event) throws IOException {
        StartScreenController.sceneGenerator(stage, root, "EditProfileScreen-view.fxml", event, "Edit Profile Screen");
    }

    private void initializeStatsList() {
        Player currentPlayer = LoginManager.getCurrentPlayer();
        statsList.getItems().clear();
        for(Level level : currentPlayer.getScoreMap().keySet()) {
            statsList.getItems().add("Level " + level.getLevelNum() + ": Total wins: " + currentPlayer.getScoreMap().get(level));
        }
    }

    @FXML
    void initialize() {
        assert profileImage != null : "fx:id=\"Icon\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert phoneNumberField != null : "fx:id=\"phoneNumberField\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert statsList != null : "fx:id=\"statsList\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";

        initializeFields();
        initializeStatsList();
    }

    public void initializeFields(){
        userNameTextField.setText(LoginManager.getCurrentPlayer().getName());
        passwordField.setText(LoginManager.getCurrentPlayer().getPassword());
        phoneNumberField.setText(LoginManager.getCurrentPlayer().getPhoneNumber());
        String profileImageUrl = LoginManager.getCurrentPlayer().getProfileImage();
        profileImage.setImage((new Image(profileImageUrl)));
    }

}

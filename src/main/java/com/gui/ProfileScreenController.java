package com.gui;

import java.io.IOException;
import java.util.Objects;

import com.game.LoginManager;
import com.game.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
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
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        configureStage(stage, "Main");
    }

    @FXML
    void updateClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditProfileScreen-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        configureStage(stage, "EditProfileScreen");
    }

    private void configureStage(Stage stage, String stageTitle) {
        stage.getScene().setRoot(root);
        stage.setFullScreen(false);
        stage.setResizable(true);
        stage.setTitle(stageTitle + " " + "Screen");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }

    private void initializeStatsList() {
        Player currentPlayer = LoginManager.getCurrentPlayer();
        statsList.getItems().clear();
        for(Integer i : currentPlayer.getScoreMap().keySet()) {
            statsList.getItems().add("Level " + i + ": " + currentPlayer.getScoreMap().get(i));
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

        userNameTextField.setText(LoginManager.getCurrentPlayer().getName());
        passwordField.setText(LoginManager.getCurrentPlayer().getPassword());
        phoneNumberField.setText(LoginManager.getCurrentPlayer().getPhoneNumber());
        String profileImageUrl = LoginManager.getCurrentPlayer().getProfileImage();
        profileImage.setImage((new Image(profileImageUrl)));

        initializeStatsList();
    }

}

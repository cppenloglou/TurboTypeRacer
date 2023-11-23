package com.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.game.LoginManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML
    private ImageView goBackButton, leaderboardBtn, levelsBtn, profileBtn, settingsBtn;
    private Stage stage;
    private Parent root;

    @FXML
    void leaderboardClicked(MouseEvent event) {

    }

    @FXML
    void levelsClicked(MouseEvent event) {

    }

    @FXML
    void profileClicked(MouseEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ProfileScreen-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        configureStage(stage, "Profile");
    }

    @FXML
    void settingsClicked(MouseEvent event) {

    }

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
    void initialize() {
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert leaderboardBtn != null : "fx:id=\"leaderboardBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert levelsBtn != null : "fx:id=\"levelsBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert profileBtn != null : "fx:id=\"profileBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert settingsBtn != null : "fx:id=\"settingsBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        LoginManager loginManager = new LoginManager("players.txt");
        System.out.println("Name: " + loginManager.getCurrentPlayer().getName() + "\nPassword: " + loginManager.getCurrentPlayer().getPassword() +
                "\nPhone Number: " + loginManager.getCurrentPlayer().getPhoneNumber() + "\nImage Url: " + loginManager.getCurrentPlayer().getProfileImage());
    }

}

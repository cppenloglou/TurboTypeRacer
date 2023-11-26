package com.gui;

import java.io.IOException;

import com.game.LoginManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML
    private ImageView goBackButton, leaderboardBtn, levelsBtn, profileBtn, settingsBtn;
    private Stage stage;
    private Parent root;

    @FXML
    void leaderboardClicked(MouseEvent event) throws IOException {
        StartScreenController.sceneGenerator(stage, root, "LeaderBoardScreen-view.fxml", event, "Leader Board Screen");
    }

    @FXML
    void levelsClicked(MouseEvent event) throws IOException {
        StartScreenController.sceneGenerator(stage, root, "LevelsScreen-view.fxml", event, "Levels Screen");
    }

    @FXML
    void profileClicked(MouseEvent event) throws IOException {
        StartScreenController.sceneGenerator(stage, root, "ProfileScreen-view.fxml", event, "Profile Screen");
    }

    @FXML
    void settingsClicked(MouseEvent event) throws IOException {
        StartScreenController.sceneGenerator(stage, root, "SettingsScreen-view.fxml", event, "Settings Screen");
    }

    @FXML
    void returnToMain(MouseEvent event) throws IOException {
        LoginManager.setCurrentPlayer(null);
        SettingsScreenController.loadDefaultSettings("settings.txt");
        StartScreenController.sceneGenerator(stage, root, "StartScreen-view.fxml", event, "Start Screen");
    }

    @FXML
    void initialize() {
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert leaderboardBtn != null : "fx:id=\"leaderboardBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert levelsBtn != null : "fx:id=\"levelsBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert profileBtn != null : "fx:id=\"profileBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
        assert settingsBtn != null : "fx:id=\"settingsBtn\" was not injected: check your FXML file 'MainScreen-view.fxml'.";
    }

}

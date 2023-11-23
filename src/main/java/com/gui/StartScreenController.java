package com.gui;

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
    private ImageView loginButton, signUpButton, quitButton;

    @FXML
    void initialize() {

    }

    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        if (event.getSource() == loginButton)
            sceneGenerator("LoginScreen-view.fxml", event, "Login Screen");
        else if (event.getSource() == signUpButton)
            sceneGenerator("SignUpScreen-view.fxml", event, "SignUp Screen");
        else if (event.getSource() == quitButton)
            System.exit(0);
    }

    private void sceneGenerator(String name, MouseEvent event, String Player_Screen) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name)));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.setFullScreen(false);
        stage.setResizable(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle(Player_Screen);
        stage.show();
    }

}

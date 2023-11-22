package com.example.turbotyperacer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Parent root;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        stage.setTitle("Start Screen");
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setResizable(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.game.Level;
import com.game.LoginManager;
import com.game.Player;
import com.game.Vehicle;
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
    private ImageView profileImage, goBackButton, updateBtn, upArrowBtn, downArrowBtn, garageVehicleSelectionImageView;

    @FXML
    private TextField userNameTextField, phoneNumberField, passwordField;

    @FXML
    private ListView<String> statsList;

    private Stage stage;
    private Parent root;

    String vehicle = "/assets/vehicles/car";
    int numberOfcar = 1;


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
    void changeCar(MouseEvent event) {

        Player currentPlayer = LoginManager.getCurrentPlayer();
        ArrayList<Vehicle> garage = currentPlayer.getGarage();
        int currentPlayerAvailableCars = garage.size();

        if (event.getSource() == downArrowBtn)
            numberOfcar++;

        else if (event.getSource() == upArrowBtn)
            numberOfcar--;

        String input = garage.get(numberOfcar - 1).getImageUrl();
        int index = input.lastIndexOf("r") + 1;
        System.out.println("index: " + index);
        int number = Integer.parseInt(String.valueOf(input.charAt(index)));
        System.out.println("number: " + number);

        // Create the new string
        String result = "/assets/vehicles/car" + number + ".png";

        garageVehicleSelectionImageView.setImage(new Image(Objects.requireNonNull(StartScreen.class.getResourceAsStream(result))));



        if (numberOfcar == 1) {downArrowBtn.setVisible(true); upArrowBtn.setVisible(false);}
        else if (numberOfcar > 1 && numberOfcar < currentPlayerAvailableCars) {downArrowBtn.setVisible(true); upArrowBtn.setVisible(true);}
        else if (numberOfcar == currentPlayerAvailableCars) {downArrowBtn.setVisible(false); upArrowBtn.setVisible(true);}


    }

    @FXML
    void initialize() {
        assert downArrowBtn != null : "fx:id=\"downArrowBtn\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert garageVehicleSelectionImageView != null : "fx:id=\"garageVehicleSelectionImageView\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert phoneNumberField != null : "fx:id=\"phoneNumberField\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert profileImage != null : "fx:id=\"profileImage\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert statsList != null : "fx:id=\"statsList\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert upArrowBtn != null : "fx:id=\"upArrowBtn\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";
        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'ProfileScreen-view.fxml'.";

        Player currentPlayer = LoginManager.getCurrentPlayer();
        ArrayList<Vehicle> garage = currentPlayer.getGarage();
        int currentPlayerAvailableCars = garage.size();
        if(currentPlayerAvailableCars > 1) {downArrowBtn.setVisible(true);}

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

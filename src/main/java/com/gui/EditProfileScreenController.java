package com.gui;

import com.game.LoginManager;
import com.game.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EditProfileScreenController {
    private final ArrayList<ImageView> iconList = new ArrayList<>();

    @FXML
    private TextField userNameTextField, phoneNumberField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView greenIcon, redIcon, blueIcon, purpleIcon;

    @FXML
    private ImageView goBackButton;

    private String profileImage;

    private Stage stage;
    private Parent root;


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

    public ImageView getIconImageView(String imageUrl){
        if (imageUrl.equals(redIcon.getImage().getUrl())) {
            return redIcon;
        } else if (imageUrl.equals(blueIcon.getImage().getUrl())) {
            return blueIcon;
        } else if (imageUrl.equals(greenIcon.getImage().getUrl())) {
            return greenIcon;
        } else if (imageUrl.equals(purpleIcon.getImage().getUrl())) {
            return purpleIcon;
        }

        return null;
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
    void updateClicked(MouseEvent event) throws IOException {
        String name = userNameTextField.getText();
        String password = passwordField.getText();
        String phoneNumber = phoneNumberField.getText();

        if(name!=null && password!=null && phoneNumber!=null && profileImage!=null){
            LoginManager loginManager = new LoginManager("players.txt");
            if(!loginManager.playerExists(name)){
                Player updatedPlayer = loginManager.getPlayer(LoginManager.getCurrentPlayer().getName(), LoginManager.getCurrentPlayer().getPassword());
                updatedPlayer.setName(name);
                updatedPlayer.setPassword(password);
                updatedPlayer.setPhoneNumber(phoneNumber);
                updatedPlayer.setImage(profileImage);
                loginManager.savePlayerDatabase("players.txt");
                loginManager.setCurrentPlayer(updatedPlayer);

                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen-view.fxml")));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                configureStage(stage, "Main");
            }
        }
    }

    @FXML
    void initialize() {
        assert blueIcon != null : "fx:id=\"blueIcon\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        assert greenIcon != null : "fx:id=\"greenIcon\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        assert phoneNumberField != null : "fx:id=\"phoneNumberField\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        assert purpleIcon != null : "fx:id=\"purpleIcon\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        assert redIcon != null : "fx:id=\"redIcon\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        assert userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";
        iconList.add(redIcon);
        iconList.add(blueIcon);
        iconList.add(greenIcon);
        iconList.add(purpleIcon);

        userNameTextField.setText(LoginManager.getCurrentPlayer().getName());
        passwordField.setText(LoginManager.getCurrentPlayer().getPassword());
        phoneNumberField.setText(LoginManager.getCurrentPlayer().getPhoneNumber());
        profileImage = LoginManager.getCurrentPlayer().getProfileImage();
        ImageView selectedImage = getIconImageView(profileImage);
        selectedImage.setStyle("-fx-effect: dropShadow(three-pass-box, " + getIconColour(selectedImage) + ", 30, 0.5, 0, 0)");
    }

}

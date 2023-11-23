package com.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String password;
    private String phoneNumber;
    private String profileImage;

    public Player(String name, String password, String phoneNumber, String profileImage){
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImage(String profileImage){
        this.profileImage = profileImage;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}

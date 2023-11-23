package com.game;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Player implements Serializable {
    private String name;
    private String password;
    private String phoneNumber;
    private String profileImage;
    private HashMap<Integer, Integer> scoreMap = new HashMap<>();

    public Player(String name, String password, String phoneNumber, String profileImage){
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        IntializeScoreMap();
    }

    public void IntializeScoreMap() {
        for(int i = 0; i < 3; i++) {
            scoreMap.put(i, 0);
        }
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public HashMap<Integer, Integer> getScoreMap() {
        return scoreMap;
    }
}

package com.game;

import javafx.scene.image.ImageView;

public class PlayerData {
    private final ImageView icon;
    private final String playerName;
    private final int wins;

    public PlayerData(ImageView icon, String playerName, int wins) {
        this.icon = icon;
        this.playerName = playerName;
        this.wins = wins;
    }

    public String getPlayer() {
        return playerName;
    }

    public ImageView getIcon() {
        return icon;
    }

    public int getWins() {
        return wins;
    }
}


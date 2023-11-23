package com.game;

import java.io.*;
import java.util.ArrayList;

public class LoginManager {

    private static Player currentPlayer;
    private static ArrayList<Player> playerDatabase = new ArrayList<>();

    public LoginManager(String fileName){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            playerDatabase = (ArrayList<Player>) in.readObject();
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public void savePlayerDatabase(String fileName){
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(playerDatabase);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean playerExists(String name){
        if(playerDatabase==null){
            return false;
        }
        for(Player p : playerDatabase){
            if(p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public boolean isValidLogin(String username, String password) {
        if(playerDatabase==null){
            return false;
        }

        for(Player p : playerDatabase){
            if(p.getName().equals(username) && p.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

    public void addUser(Player p) {
        playerDatabase.add(p);
    }

    public void addCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    public Player getPlayer(String username, String password) {

        if(playerDatabase==null){
            return null;
        }

        for (Player p : playerDatabase) {
            if(p.getName().equals(username) && p.getPassword().equals(password)){
                return p;
            }
        }

        return null;
    }

    public void setCurrentPlayer(Player tempPlayer) {
        currentPlayer = tempPlayer;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
}

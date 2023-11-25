package com.game;

import com.gui.StartScreen;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class LoginManager {

    private static Player currentPlayer;
    private static ArrayList<Player> playerDatabase = new ArrayList<>();

    public static void initializeLoginManager(String fileName) {
        String path = Objects.requireNonNull(StartScreen.class.getResource("/playerDb/")).getPath()+fileName;
        File dbFile = new File(path);
        if(!dbFile.exists()){
            try{
                dbFile.createNewFile();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        if(dbFile.length()!=0){
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
                playerDatabase = (ArrayList<Player>) in.readObject();
            } catch (IOException | ClassNotFoundException e ) {
                e.printStackTrace();
            }
        }
    }

    public static void savePlayerDatabase(String fileName){
        if(!playerDatabase.isEmpty()){
            String path = Objects.requireNonNull(StartScreen.class.getResource("/playerDb/" + fileName)).getPath();
            try (FileOutputStream fos = new FileOutputStream(path);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(playerDatabase);
                oos.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean playerExists(String name){
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
    public static boolean isValidLogin(String username, String password) {
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

    public static void addUser(Player p) {
        playerDatabase.add(p);
    }

    public static void addCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    public static Player getPlayer(String username, String password) {

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

    public static void setCurrentPlayer(Player tempPlayer) {
        currentPlayer = tempPlayer;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static ArrayList<Player> getPlayerDatabase() {
        return playerDatabase;
    }
}

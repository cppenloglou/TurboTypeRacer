package com.game;

import com.gui.StartScreen;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class LevelManager {

    private static Level currentLevel;
    private static ArrayList<Level> levelDatabase = new ArrayList<>();

    public static void initializeLevels() {
        if(levelDatabase.isEmpty()){
            String path = Objects.requireNonNull(StartScreen.class.getResource("/levelDb")).getPath();
            File directory = new File(path);
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();

                if(files!=null){
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".txt")) {
                            String level = file.getName().substring(0, file.getName().lastIndexOf('.'));
                            levelDatabase.add(new Level(Integer.parseInt(level), file.getAbsolutePath()));
                        }
                    }
                }
            } else {
                System.err.println("Specified path is not a directory.");
            }
        }
    }

    public static ArrayList<Level> getLevelDatabase(){
        return levelDatabase;
    }

    public static void setCurrentLevel(Level level){
        currentLevel = level;
    }

    public static Level getCurrentLevel(){
        return currentLevel;
    }

    public static Level getLevel(int level){
        for(Level l : levelDatabase){
            if(l.getLevelNum()==level) return l;
        }
        return null;
    }
}

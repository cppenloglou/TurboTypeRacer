package com.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Level implements Serializable {
    private final int levelNum;
    private final String levelPath;
    private final ArrayList<String> wordsList = new ArrayList<>();

    public Level(int levelNum, String levelPath){
        this.levelNum = levelNum;
        this.levelPath = levelPath;
    }

    public void readFile(){
        if(wordsList.isEmpty()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(levelPath));
                String line;
                while ((line = reader.readLine()) != null) {
                    wordsList.add(line);
                }
                reader.close();
            } catch (IOException e) {
                System.err.println("Error reading file: " + levelPath + ".txt");
                e.printStackTrace();
            }
        }
    }

    public int getLevelNum(){
        return this.levelNum;
    }

    public String getLevelPath(){
        return this.levelPath;
    }

    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) return false;
        Level l = (Level) object;
        return this.levelNum == l.getLevelNum() && this.levelPath.equals(l.getLevelPath());
    }

    public int hashCode() {
        return Objects.hash(levelNum);
    }
}

package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.logging.Level;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Model
{
    public static final int FIELD_SELL_SIZE = 20;
    EventListener eventListener;
    GameObjects gameObjects;
    int currentLevel = 1;
    LevelLoader levelLoader = new LevelLoader(Paths.get("../res/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        gameObjects = new GameObjects(
                levelLoader.getLevel(currentLevel).getWalls(),
                levelLoader.getLevel(currentLevel).getBoxes(),
                levelLoader.getLevel(currentLevel).getHomes(),
                levelLoader.getLevel(currentLevel).getPlayer()
                );
        return gameObjects;
    }
    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restartLevel(currentLevel);
    }
}

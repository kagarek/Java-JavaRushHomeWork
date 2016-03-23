package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;
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
    LevelLoader levelLoader = new LevelLoader(Paths.get("src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

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

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (!checkWallCollision(player,direction) && !checkBoxCollision(direction))
        {
            switch (direction)
            {
                case LEFT:
                    player.move(-FIELD_SELL_SIZE, 0);
                    break;
                case RIGHT:
                    player.move(FIELD_SELL_SIZE, 0);
                    break;
                case UP:
                    player.move(0, -FIELD_SELL_SIZE);
                    break;
                case DOWN:
                    player.move(0, FIELD_SELL_SIZE);
                    break;
                default:
                    break;
            }
            checkCompletion();
        }
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (CollisionObject wall : gameObjects.getWalls())
            if (gameObject.isCollision(wall,direction))
                return true;
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();

        for (Box b : boxes){
            if (player.isCollision(b,direction))
            {
                if (checkWallCollision(b, direction)) return true;

                for (Box b1 : boxes)
                    if (b.isCollision(b1,direction)) return true;

                switch (direction) {
                    case LEFT:
                        b.move(-FIELD_SELL_SIZE,0);
                        break;
                    case RIGHT:
                        b.move(FIELD_SELL_SIZE,0);
                        break;
                    case UP:
                        b.move(0,-FIELD_SELL_SIZE);
                        break;
                    case DOWN:
                        b.move(0,FIELD_SELL_SIZE);
                        break;
                    default:
                        break;
                }

                break;
            }
        }

        return false;
    }

    public void checkCompletion() {
        Set<Home> homes = gameObjects.getHomes();
        Set<Box> boxes = gameObjects.getBoxes();
        int properPositions = 0;

        for (Home h : homes)
            for (Box b : boxes)
                if (h.getX() == b.getX() && h.getY() == b.getY())
                    properPositions++;

        if (properPositions == homes.size())
            eventListener.levelCompleted(currentLevel);
    }
}
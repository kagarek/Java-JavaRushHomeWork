package com.javarush.test.level34.lesson15.big01.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class LevelLoader
{
    public LevelLoader(Path levels)
    {
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = new Player(Model.FIELD_SELL_SIZE/2,Model.FIELD_SELL_SIZE/2);
        walls.add(new Wall(Model.FIELD_SELL_SIZE/2,Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(Model.FIELD_SELL_SIZE/2,Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(Model.FIELD_SELL_SIZE/2,Model.FIELD_SELL_SIZE/2));
        boxes.add(new Box(Model.FIELD_SELL_SIZE/2,Model.FIELD_SELL_SIZE/2));
        homes.add(new Home(Model.FIELD_SELL_SIZE/2,Model.FIELD_SELL_SIZE/2));

        GameObjects gameObjects = new GameObjects(walls,boxes,homes,player);

        return gameObjects;
    }
}

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
        Player player = new Player(16*Model.FIELD_SELL_SIZE/2,16*Model.FIELD_SELL_SIZE/2);
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        walls.add(new Wall(2*Model.FIELD_SELL_SIZE/2,20*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(8*Model.FIELD_SELL_SIZE/2,20*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(14*Model.FIELD_SELL_SIZE/2,20*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(20*Model.FIELD_SELL_SIZE/2,20*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(26*Model.FIELD_SELL_SIZE/2,20*Model.FIELD_SELL_SIZE/2));

        walls.add(new Wall(4*Model.FIELD_SELL_SIZE/2,10*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(10*Model.FIELD_SELL_SIZE/2,10*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(16*Model.FIELD_SELL_SIZE/2,10*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(22*Model.FIELD_SELL_SIZE/2,10*Model.FIELD_SELL_SIZE/2));

        walls.add(new Wall(2*Model.FIELD_SELL_SIZE/2,18*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(2*Model.FIELD_SELL_SIZE/2,16*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(2*Model.FIELD_SELL_SIZE/2,14*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(2*Model.FIELD_SELL_SIZE/2,12*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(2*Model.FIELD_SELL_SIZE/2,10*Model.FIELD_SELL_SIZE/2));

        walls.add(new Wall(26*Model.FIELD_SELL_SIZE/2,18*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(26*Model.FIELD_SELL_SIZE/2,16*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(26*Model.FIELD_SELL_SIZE/2,14*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(26*Model.FIELD_SELL_SIZE/2,12*Model.FIELD_SELL_SIZE/2));
        walls.add(new Wall(26*Model.FIELD_SELL_SIZE/2,10*Model.FIELD_SELL_SIZE/2));

        boxes.add(new Box(12*Model.FIELD_SELL_SIZE/2,12*Model.FIELD_SELL_SIZE/2));

        homes.add(new Home(18*Model.FIELD_SELL_SIZE/2,14*Model.FIELD_SELL_SIZE/2));

        return new GameObjects(walls,boxes,homes,player);
    }
}

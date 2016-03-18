package com.javarush.test.level34.lesson15.big01.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class GameObjects
{
    Set<Wall> walls;
    Set<Box> boxes;
    Set<Home> homes;
    Player player;

    public Player getPlayer()
    {
        return player;
    }

    public Set<Home> getHomes()
    {
        return homes;
    }

    public Set<Box> getBoxes()
    {
        return boxes;
    }

    public Set<Wall> getWalls()
    {
        return walls;
    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player)
    {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> allObjects = new HashSet<>();
        allObjects.addAll(walls);
        allObjects.addAll(boxes);
        allObjects.addAll(homes);
        allObjects.add(player);
        return allObjects;
    }
}

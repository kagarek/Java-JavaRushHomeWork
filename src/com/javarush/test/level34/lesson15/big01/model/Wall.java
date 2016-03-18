package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(getX(),getY(),getWidth(),getHeight());
    }
}

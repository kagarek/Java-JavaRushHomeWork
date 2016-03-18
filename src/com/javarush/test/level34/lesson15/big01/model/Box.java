package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void move(int x, int y)
    {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }
}

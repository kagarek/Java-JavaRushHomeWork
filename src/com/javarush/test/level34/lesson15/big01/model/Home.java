package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.ORANGE);
        graphics.drawOval(getX(),getY(),getWidth(),getHeight());
    }
}

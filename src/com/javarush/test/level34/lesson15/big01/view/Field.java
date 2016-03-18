package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Field extends JPanel
{
    View view;
    EventListener eventListener;

    public Field(View view)
    {
        this.view = view;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());
        GameObjects gameObjects = view.getGameObjects();
        Set<GameObject> gameObjectSet = gameObjects.getAll();
        for (GameObject gameObject : gameObjectSet){
            gameObject.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}

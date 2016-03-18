package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;

    public Field(View view)
    {
        this.view = view;
        KeyHandler k = new KeyHandler(this);
        addKeyListener(k);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
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

    public static class KeyHandler extends KeyAdapter
    {
        private Field field;

        public KeyHandler(Field field){
            this.field = field;
        }
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT : {
                    field.eventListener.move(Direction.LEFT);
                    break;
                }
                case KeyEvent.VK_RIGHT : {
                    field.eventListener.move(Direction.RIGHT);
                    break;
                }
                case KeyEvent.VK_UP : {
                    field.eventListener.move(Direction.UP);
                    break;
                }
                case KeyEvent.VK_DOWN : {
                    field.eventListener.move(Direction.DOWN);
                    break;
                }
                case KeyEvent.VK_R : {
                    field.eventListener.restart();
                    break;
                }
                default: break;
            }
        }
    }

}

package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.view.View;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class Controller implements EventListener
{
    View view;
    Model model;

    public Controller()
    {
        this.view = new View(this);
        this.model = new Model();
        view.init();
    }

    public static void main(String[] args)
    {
        Controller controller = new Controller();
    }

    @Override
    public void move(Direction direction)
    {

    }

    @Override
    public void restart()
    {

    }

    @Override
    public void startNextLevel()
    {

    }

    @Override
    public void levelCompleted(int level)
    {

    }

    public GameObjects getGameObjects(){ return model.getGameObjects();    }
}

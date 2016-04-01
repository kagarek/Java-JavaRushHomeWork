package com.javarush.test.level36.lesson04.big01.controller;

import com.javarush.test.level36.lesson04.big01.model.Model;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public class Controller
{
    public void setModel(Model model)
    {
        this.model = model;
    }

    private Model model;

    public void onShowAllUsers() {
        model.loadUsers();
    }

}

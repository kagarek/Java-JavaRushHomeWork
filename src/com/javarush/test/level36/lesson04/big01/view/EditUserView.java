package com.javarush.test.level36.lesson04.big01.view;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.ModelData;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public class EditUserView implements View
{
    @Override
    public void refresh(ModelData modelData)
    {
        System.out.println("User to be edited:");
        System.out.println("\t"+modelData.getActiveUser());
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller)
    {

    }
}
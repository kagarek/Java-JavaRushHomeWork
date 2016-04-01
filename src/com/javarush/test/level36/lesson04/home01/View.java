package com.javarush.test.level36.lesson04.home01;

import java.util.List;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public class View
{
    private Controller controller = new Controller();

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}

package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

/**
 * Created by igormakarychev on 6/21/15.
 */
public class Restaurant
{
    public static void main(String[] args)
    {
        //new Tablet(5).createOrder();

        Cook cook1 = new Cook("Amigo1");
        Cook cook2 = new Cook("Amigo2");
        Cook cook3 = new Cook("Amigo3");

        Waitor waitor = new Waitor();
        Tablet tablet = new Tablet(5);

        DirectorTablet directorTablet = new DirectorTablet();

        tablet.addObserver(cook1);
        cook1.addObserver(waitor);
        cook2.addObserver(waitor);
        cook3.addObserver(waitor);

        tablet.createOrder();

        //во время второго заказа переводишь системное время на день вперед\назад.
        tablet.deleteObserver(cook1);
        tablet.addObserver(cook2);
        tablet.createOrder();

        //во время третьего заказа переводишь системное время на день вперед\назад.
//        tablet.deleteObserver(cook2);
//        tablet.addObserver(cook3);
//        tablet.createOrder();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by igormakarychev on 6/21/15.
 */
public class Cook extends Observable implements Observer
{
    private String name;

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order.toString()
                + ", cooking time " + order.getTotalCookingTime() + "min");

        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), this.toString(), order.getTotalCookingTime()*60, order.getDishes()));

        new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();

        //addObserver(new Waitor());
        setChanged();
        notifyObservers(order);
    }
}

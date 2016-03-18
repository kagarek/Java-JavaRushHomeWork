package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by igormakarychev on 6/21/15.
 */
public class Tablet extends Observable
{
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    @Override
    public String toString()
    {
        return "Tablet{number=" + number + "}";
    }

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder()
    {
        Order order = null;
        try
        {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            //addObserver(new Cook("Amigo"));
            try
            {
                if (!order.isEmpty())
                {
                    setChanged();
                    notifyObservers(order);
                }
            }
            catch (NoVideoAvailableException e)
            {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE,"Console is unavailable.");
        }
    }
}

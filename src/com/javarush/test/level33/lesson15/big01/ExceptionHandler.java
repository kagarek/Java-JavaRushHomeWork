package com.javarush.test.level33.lesson15.big01;

/**
 * Created by igormakarychev on 1/12/16.
 */
public class ExceptionHandler
{
    public static void log(Exception e)
    {
        Helper.printMessage(e.getMessage());
    }
}


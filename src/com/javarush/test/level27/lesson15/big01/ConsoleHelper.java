package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;
import static com.javarush.test.level27.lesson15.big01.kitchen.Dish.allDishesToString;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igormakarychev on 6/21/15.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        writeMessage("Choose dishes:");
        writeMessage(allDishesToString());
        while (true)
        {
            String line = readString();
            if (line.equalsIgnoreCase("exit"))
                break;
            else
            {
                try
                {
                    Dish dish = Dish.valueOf(line);
                    dishes.add(dish);
                }
                catch (IllegalArgumentException e)
                {
                    writeMessage(line + " is not detected");
                }
            }
        }

        return dishes;
    }

}

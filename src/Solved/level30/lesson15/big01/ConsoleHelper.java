package solved.level30.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Igor_Makarychev on 10.11.2015.
 */
public class ConsoleHelper
{
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString()
    {
        String s = "";

        try
        {
            s = bufferedReader.readLine();
        }
        catch (IOException io)
        {
            writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            s = readString();
        }

        return s;
    }

    public static int readInt()
    {
        int i;

        try
        {
            i = Integer.parseInt(readString());
        }
        catch (NumberFormatException nfe)
        {
            writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            i = readInt();
        }

        return i;
    }

}


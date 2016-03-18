package solved.level08.lesson11.home09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        isDateOdd(s);
    }

    public static boolean isDateOdd(String s)
    {

        Date currentDate = new Date(s);

        Date yearStart = new Date();

        yearStart.setYear(currentDate.getYear());
        yearStart.setMonth(0);
        yearStart.setDate(1);
        yearStart.setHours(0);
        yearStart.setMinutes(0);
        yearStart.setSeconds(0);

        currentDate.setHours(23);
        currentDate.setMinutes(59);
        currentDate.setSeconds(59);

        long diff = currentDate.getTime()-yearStart.getTime();
        long msDay = 1000*60*60*24;

        int dayCount = (int) (diff/msDay);

        boolean x;

        if (dayCount % 2 == 0) x = true;
        else x = false;

        System.out.println(x);

        return x;
    }
}

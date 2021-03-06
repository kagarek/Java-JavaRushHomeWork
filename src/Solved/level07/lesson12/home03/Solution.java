package solved.level07.lesson12.home03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Максимальное и минимальное числа в массиве
Создать массив на 20 чисел. Заполнить его числами с клавиатуры. Найти максимальное и минимальное числа в массиве.
Вывести на экран максимальное и минимальное числа через пробел.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int  maximum;
        int  minimum;

        //Напишите тут ваш код

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i<20;i++)
        {
            list.add(Integer.parseInt(reader.readLine()));
        }

        maximum = list.get(0);
        minimum = list.get(0);

        for (Integer x : list)
        {
            if (x>maximum)
                maximum=x;

            if (x<minimum)
                minimum=x;
        }


        System.out.println(maximum);
        System.out.println(minimum);
    }
}

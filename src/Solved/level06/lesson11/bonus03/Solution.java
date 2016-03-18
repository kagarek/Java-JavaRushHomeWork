package solved.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        //Напишите тут ваш код

        int a[] = new int[5];

        for (int i=0;i<5;i++)
        {
            a[i] = Integer.parseInt(reader.readLine());
        }


        for (int i = 0; i < a.length; i++) {

            int min = a[i];
            int imin = i;

            for (int j = i+1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    imin = j;
                }
            }

            if (i != imin) {
                int temp = a[i];
                a[i] = a[imin];
                a[imin] = temp;
            }
        }

        for (int i=0;i<a.length;i++)
            System.out.println(a[i]);
    }
}

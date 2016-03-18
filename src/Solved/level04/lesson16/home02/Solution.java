package solved.level04.lesson16.home02;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        //Напишите тут ваш код

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n1 = Integer.parseInt(bufferedReader.readLine());
        int n2 = Integer.parseInt(bufferedReader.readLine());
        int n3 = Integer.parseInt(bufferedReader.readLine());

        if (n1 < n2)
        {
            if (n2 < n3)
                System.out.println(n2);
            else
            {
                if (n1 < n3)
                    System.out.println(n3);
                else
                    System.out.println(n1);
            }
        }
        else
        {
            if (n1 < n3)
                System.out.println(n1);
            else
            {
                if (n2 < n3)
                    System.out.println(n3);
                else
                    System.out.println(n2);
            }
        }
    }
}

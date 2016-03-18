package solved.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        boolean check = true;

        while (check)
        {
            String input = bufferedReader.readLine();

            if (!input.equals("сумма"))
                sum = sum + Integer.parseInt(input);
            else
            {
                check = false;
            }

        }

        System.out.println(sum);

    }
}

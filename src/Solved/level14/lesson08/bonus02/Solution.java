package solved.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(bufferedReader.readLine());
        int b = Integer.parseInt(bufferedReader.readLine());
        System.out.println(NOD(a,b));

    }

    public static int NOD(int a1, int a2)
    {
        int a = a1;
        int b = a2;

        int tmp;

        while (a % b > 0)
        {
            tmp = b;
            b = a - b * (a / b);
            a = tmp;
        }
        return b;

    }

}



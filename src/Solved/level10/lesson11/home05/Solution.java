package solved.level10.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* Количество букв
Ввести с клавиатуры 10 строчек и подсчитать в них количество различных букв (для 33 букв алфавита).  Вывести результат на экран.
Пример вывода:
а 5
б 8
в 3
г 7
…
я 9
*/

public class Solution
{
    public static void main(String[] args)  throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //алфавит
        ArrayList<Character> alphabet = new ArrayList<Character>();
        for(int i=0;i<32;i++)
        {
            alphabet.add( (char) ('а'+i));
        }
        alphabet.add(6,'ё');

        //ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            String s = reader.readLine();
            list.add( s.toLowerCase());
        }


        //Напишите тут ваш код
        //Map<Character,Integer> result = new HashMap<Character, Integer>();
        String[][] result = new String[33][2];
        int count = 0;

        //go by letters
        for (int i = 0; i < alphabet.size(); i++)
        {
            // go by strings
            for (String s : list)
                // go by symbols of the string
                for (int j = 0; j < s.length(); j++)
                    if (s.charAt(j) == alphabet.get(i))
                        count++;
            System.out.println(alphabet.get(i) + " " + count);
            count = 0;
        }
    }

}

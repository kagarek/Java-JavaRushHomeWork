package solved.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        // напишите тут ваш код

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(bufferedReader.readLine())));
        //BufferedReader fileReader = new BufferedReader(new FileReader(new File("./resources/TestNumbers.txt")));
        ArrayList<Integer> array = new ArrayList<Integer>();
        String fileLine;

        while ((fileLine = fileReader.readLine()) != null)
        {
            int a = Integer.parseInt(fileLine);
            if (a % 2 == 0)
                array.add(a);
        }

        for (int i = 0; i < array.size(); i++)
            for (int j=0; j < array.size(); j++)
                {
                    if (array.get(i) < array.get(j))
                    {
                        int tmp = array.get(i);
                        array.set(i,array.get(j));
                        array.set(j,tmp);
                    }
                }

        for (int x : array)
            System.out.println(x);

        fileReader.close();
        bufferedReader.close();
    }
}

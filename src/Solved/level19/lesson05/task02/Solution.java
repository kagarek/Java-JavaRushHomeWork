package solved.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть поток ввода.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String file = consoleReader.readLine();

        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        String line = "";

        int counter=0;

        while ((line=fileReader.readLine())!=null)
        {
            String[] splittedLine = line.replaceAll("\\p{P}", " ").split(" ");

            for (int i = 0; i < splittedLine.length; i++)
            {
                if (splittedLine[i].toLowerCase().equals("world"))
                {
                    counter++;
                }
            }
        }

        System.out.println(counter);
        fileReader.close();
        consoleReader.close();

    }
}
package solved.level19.lesson10.home04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("А");
        words.add("Б");
        words.add("В");
    }

    public static void main(String[] args) throws IOException
    {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String filename = consoleReader.readLine();

        BufferedReader fileReader = new BufferedReader(new FileReader(filename));

        String line;
        int counter = 0;
        List<String> checkedWords = new ArrayList<String>();

        while ((line=fileReader.readLine())!=null)
        {
            String[] lineWords = line.replaceAll("\\p{Punct}","").split(" ");

            for (int i = 0; i< lineWords.length; i++)
            {
                if (words.contains(lineWords[i]) && !checkedWords.contains(lineWords[i]))
                {
                    counter++;
                    checkedWords.add(lineWords[i]);
                }
            }

            if (counter==2)
            {
                System.out.println(line);
            }

            counter=0;
            checkedWords.clear();
        }

    }
}

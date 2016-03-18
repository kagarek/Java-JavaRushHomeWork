package solved.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример, "мор"-"ром", "трос"-"сорт"
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = bufferedReader.readLine();
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line=fileReader.readLine())!=null)
            {
                stringBuilder.append(line);
                stringBuilder.append(" ");
            }

            line = stringBuilder.toString().trim().replace("  ", " ");
            String[] splitString = line.split(" ");

            for (int i = 0; i < splitString.length; i++)
            {
                for (int j = i+1; j < splitString.length; j++)
                {
                    StringBuilder compare2 = new StringBuilder(splitString[j]);
                    if (splitString[i].equals(compare2.reverse().toString()))
                    {
                        Pair newPair = new Pair();
                        newPair.first = splitString[i];
                        newPair.second = compare2.reverse().toString();
                        result.add(newPair);
                    }
                }
            }

            System.out.println(result.toString());
        }
        catch (Exception e)
        {
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}

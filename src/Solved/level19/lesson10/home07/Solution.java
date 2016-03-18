package solved.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        FileWriter fileWriter = new FileWriter(args[1]);
        String line;
        String outputString = "";

        while ((line=fileReader.readLine())!=null)
        {
            String[] lineWords = line.split(" ");

            for (int i = 0; i < lineWords.length; i++)
            {
                if (lineWords[i].length()>6)
                {
                    outputString = outputString + lineWords[i]+",";
                }
            }
        }

        fileWriter.write(outputString.substring(0, outputString.length()-1));

        fileReader.close();
        fileWriter.close();
    }
}

package solved.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки
*/

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
        String line;

        while ((line=fileReader.readLine())!=null)
        {
            String[] lineWords = line.split(" ");

            for (int i = 0; i < lineWords.length; i++)
            {
                byte[] wordBytes = lineWords[i].getBytes();
                for (int j = 0; j < wordBytes.length; j++)
                {
                    if (wordBytes[j] >= 48  && wordBytes[j] <= 57)
                    {
                        fileOutputStream.write(wordBytes,0,wordBytes.length);
                        fileOutputStream.write(32);
                        break;
                    }
                }
            }
        }

        fileOutputStream.close();
        fileReader.close();
    }
}

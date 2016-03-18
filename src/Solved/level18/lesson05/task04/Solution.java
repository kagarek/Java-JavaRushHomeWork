package solved.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки ввода-вывода
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputFile = reader.readLine();
        String outputFile = reader.readLine();

        FileInputStream fileInputStream = new FileInputStream(inputFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

        while (fileInputStream.available() > 0)
        {
            byte[] buffer1 = new byte[fileInputStream.available()];

            byte[] buffer2 = new byte[fileInputStream.available()];

            int count = fileInputStream.read(buffer1);
            int k = 0;

            for (int i = buffer1.length-1; i>=0; i--)
            {
                buffer2[k]=buffer1[i];
                k++;
            }

            fileOutputStream.write(buffer2,0,count);
        }

        fileInputStream.close();
        fileOutputStream.close();
        reader.close();
    }
}

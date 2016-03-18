package solved.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки ввода-вывода
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputFile = reader.readLine();
        String outputFile1 = reader.readLine();
        String outputFile2 = reader.readLine();

        FileInputStream fileInputStream = new FileInputStream(inputFile);
        FileOutputStream fileOutputStream1 = new FileOutputStream(outputFile1);
        FileOutputStream fileOutputStream2 = new FileOutputStream(outputFile2);

        while (fileInputStream.available() > 0)
        {
            byte[] buffer2 = new byte[fileInputStream.available() / 2];
            byte[] buffer1 = new byte[fileInputStream.available() - buffer2.length];


            int count1 = fileInputStream.read(buffer1);
            int count2 = fileInputStream.read(buffer2);

            fileOutputStream1.write(buffer1,0,count1);
            fileOutputStream2.write(buffer2,0,count2);
        }

        fileInputStream.close();
        fileOutputStream1.close();
        fileOutputStream2.close();
        reader.close();

    }
}

package solved.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать во второй файл
Закрыть потоки
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

/*        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();*/

        String fileName1 = "resources/level18_lesson10_home05_input.txt";
        String fileName2 = "resources/level18_lesson10_home05_output.txt";

        FileInputStream fileInputStream = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName2);

        String value = "";

        while (fileInputStream.available() > 0)
        {
            int data = fileInputStream.read();
            value = value + Character.toString((char) data);

            if (data==32 || fileInputStream.available() == 0)
            {
                int k = Math.round(Float.parseFloat(value));
                value = "";
                String newK = k + " ";
                byte[] buffer;
                buffer = newK.getBytes();
                fileOutputStream.write(buffer);
            }

        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}

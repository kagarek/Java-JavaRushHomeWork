package solved.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();*/

        String fileName1 = "resources/level18_lesson10_home04_input1.txt";
        String fileName2 = "resources/level18_lesson10_home04_input2.txt";

        FileInputStream fileInputStream1 = new FileInputStream(fileName1);
        FileInputStream fileInputStream2 = new FileInputStream(fileName2);

        byte[] buffer1 = new byte[fileInputStream1.available()];
        byte[] buffer2 = new byte[fileInputStream2.available()];

        int count1 = fileInputStream1.read(buffer1);
        int count2 = fileInputStream2.read(buffer2);

        fileInputStream1.close();
        fileInputStream2.close();

        FileOutputStream fileOutputStream1 = new FileOutputStream(fileName1);

        fileOutputStream1.write(buffer2,0,count2);
        fileOutputStream1.write(buffer1,0,count1);

        fileOutputStream1.close();
    }
}

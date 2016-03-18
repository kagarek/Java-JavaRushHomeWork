package solved.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать содержимое третьего файла
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();

        /*String fileName1 = "resource/level18_lesson10_home03_output.txt";
        String fileName2 = "resource/level18_lesson10_home03_input1.txt";
        String fileName3 = "resource/level18_lesson10_home03_input2.txt";*/

        FileOutputStream fileOutputStream1 = new FileOutputStream(fileName1);
        FileInputStream fileInputStream2 = new FileInputStream(fileName2);
        FileInputStream fileInputStream3 = new FileInputStream(fileName3);

        byte[] buffer2 = new byte[fileInputStream2.available()];
        byte[] buffer3 = new byte[fileInputStream3.available()];

        int count2 = fileInputStream2.read(buffer2);
        int count3 = fileInputStream3.read(buffer3);

        fileOutputStream1.write(buffer2,0,count2);
        fileOutputStream1.write(buffer3,0,count3);

        fileInputStream2.close();
        fileInputStream3.close();
        fileOutputStream1.close();
    }
}


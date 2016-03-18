package solved.level18.lesson03.task02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Минимальный байт
Ввести с консоли имя файла
Найти минимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFile = reader.readLine();
        FileInputStream inputStream = new FileInputStream(inputFile);

        int minByte=0;
        int i=1;

        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            if (i==1)
            {
                minByte = data;
                i=0;
            }

            if (minByte > data) minByte = data;
        }

        System.out.println(minByte);

        reader.close();
        inputStream.close();
    }
}

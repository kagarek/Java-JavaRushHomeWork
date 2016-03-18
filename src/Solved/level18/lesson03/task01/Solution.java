package solved.level18.lesson03.task01;

import java.io.*;

/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFile = reader.readLine();
        FileInputStream inputStream = new FileInputStream(inputFile);

        int maxByte = 0;

        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            if (data > maxByte) maxByte = data;
        }

        System.out.println(maxByte);

        reader.close();
        inputStream.close();
    }
}

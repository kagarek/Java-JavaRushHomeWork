package solved.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
Закрыть потоки
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        FileInputStream fileInputStream = new FileInputStream(args[0]);
        int countAllSymbols = fileInputStream.available();
        int countSpaces = 0;

        while (fileInputStream.available() > 0)
        {
            int data = fileInputStream.read();
            if (data==32)
            {
                countSpaces++;
            }
        }

        double result = ((double)countSpaces)/ ((double)countAllSymbols) * 100;
        result = (int) (result * 100);
        result = result / 100;

        System.out.println(result);

        fileInputStream.close();
    }
}

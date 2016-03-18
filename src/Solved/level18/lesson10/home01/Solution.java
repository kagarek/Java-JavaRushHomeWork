package solved.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        int countEnglishLetters = 0;

        while (fileInputStream.available() > 0)
        {
            int data = fileInputStream.read();
            if ((data >= 97 && data <= 122) || (data >= 65 && data <= 90))
            {
                countEnglishLetters++;
            }
        }

        System.out.println(countEnglishLetters);

        fileInputStream.close();
    }
}

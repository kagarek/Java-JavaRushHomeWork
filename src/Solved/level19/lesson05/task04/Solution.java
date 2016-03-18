package solved.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = consoleReader.readLine();
        String file2 = consoleReader.readLine();

        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);

        while (fileReader.ready())
        {
            int i = fileReader.read();

            if (i==46)
                fileWriter.write(33);
            else
                fileWriter.write(i);
        }

        fileReader.close();
        fileWriter.close();
    }
}

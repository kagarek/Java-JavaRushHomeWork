package solved.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
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

        int i=1;

        while (fileReader.ready())
        {
            int data = fileReader.read();

            if (i % 2 == 0)
            {
                fileWriter.write(data);
            }

            i++;
        }

        fileReader.close();
        fileWriter.close();
    }
}

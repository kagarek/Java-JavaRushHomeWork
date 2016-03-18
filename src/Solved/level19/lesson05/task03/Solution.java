package solved.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки ввода-вывода.

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = consoleReader.readLine();
        String file2 = consoleReader.readLine();

        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        FileWriter fileWriter = new FileWriter(file2);

        String line = "";
        String result = "";

        while ((line=fileReader.readLine())!=null)
        {
            String[] splittedLine = line.replaceAll("[\\p{Punct}]", "").split(" ");

            for (int i = 0; i < splittedLine.length; i++)
            {
                try
                {
                    int k = Integer.parseInt(splittedLine[i]);
                    result = result+k+" ";
                }
                catch (Exception e)
                {

                }
            }
        }

        result = result.substring(0, result.length()-1);
        char[] lineWrite = result.toCharArray();
        fileWriter.write(lineWrite, 0, lineWrite.length);

        fileReader.close();
        fileWriter.close();

    }
}

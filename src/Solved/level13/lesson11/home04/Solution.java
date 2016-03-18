package solved.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести все строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        //String fileName = "resources/level13_lesson11_home04_output.txt";
        OutputStream outputStream = new FileOutputStream(new File(fileName));

        String line;
        ArrayList<String> arrayList = new ArrayList<String>();

        while (true)
        {
            line = bufferedReader.readLine();
            arrayList.add(line);

            char[] string = line.toCharArray();
            for (char s : string)
            {
                outputStream.write(s);
            }

            outputStream.write(System.getProperty("line.separator").getBytes());

            if (line.equals("exit"))
                break;
        }
    }
}

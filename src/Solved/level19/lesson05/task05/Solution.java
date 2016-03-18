package solved.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки ввода-вывода.
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов.
*/

import java.io.*;

public class Solution {
    public static void main_Option1(String[] args) throws IOException
    {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = consoleReader.readLine();
        String file2 = consoleReader.readLine();

        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);

        while (fileReader.ready())
        {
            int i = fileReader.read();

            if ((i>=33 && i<=47) || (i>=58 && i<=63) || (i>=91 && i<=96) || (i>=123 && i<=126))
            {

            }
            else
            {
                fileWriter.write(i);
            }
        }


        fileReader.close();
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException
    {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = consoleReader.readLine();
        String file2 = consoleReader.readLine();

        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        FileWriter fileWriter = new FileWriter(file2);

        String line = "";

        while ((line=fileReader.readLine())!=null)
        {
            line = line.replaceAll("[\\p{Punct}]","") + "\r\n";

            char[] lineWrite = line.toCharArray();
            fileWriter.write(lineWrite,0,lineWrite.length);
        }


        fileReader.close();
        fileWriter.close();
    }
}

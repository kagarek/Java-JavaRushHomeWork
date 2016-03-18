package solved.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение, вывести в консоль переданное неправильное имя файла и завершить работу программы
Не забудьте закрыть все потоки
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename ="";
        FileInputStream fileInputStream = null;

        while (true)
        {
            try
            {
                filename = reader.readLine();
                fileInputStream = new FileInputStream(filename);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(filename);
                fileInputStream.close();
                reader.close();
                break;
            }
        }
    }
}

package solved.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        int id = Integer.parseInt(args[0]);

        /*BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = consoleReader.readLine();
        consoleReader.close();*/

        String fileName = "resources/level18_lesson10_home07_input.txt";

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        String line;

        while ((line=fileReader.readLine())!= null)
        {
            if (Integer.parseInt(line.substring(0,line.indexOf(" "))) == id)
            {
                System.out.println(line);
            }
        }

        fileReader.close();

    }
}

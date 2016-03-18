package solved.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        TreeMap<String, Double> salaries = new TreeMap<String, Double>();
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        String line = "";

        while ((line=fileReader.readLine())!=null)
        {
            String[] record = line.split(" ");
            String record_LastName = record[0];
            double record_Value = Double.parseDouble(record[1]);

            if (!salaries.containsKey(record_LastName))
            {
                salaries.put(record_LastName,record_Value);
            }
            else
            {
                salaries.put(record_LastName,salaries.get(record_LastName)+record_Value);
            }
        }

        for (Map.Entry<String,Double> x : salaries.entrySet())
        {
            System.out.println(x.getKey() + " " + x.getValue());
        }

        fileReader.close();
    }
}

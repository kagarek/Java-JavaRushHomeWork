package solved.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
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
        Double maxSalary=0d;

        while ((line=fileReader.readLine())!=null)
        {
            String[] record = line.split(" ");
            String record_LastName = record[0];
            double record_Value = Double.parseDouble(record[1]);

            if (!salaries.containsKey(record_LastName))
            {
                salaries.put(record_LastName,record_Value);
                if (maxSalary<record_Value)
                {
                    maxSalary = record_Value;
                }
            }
            else
            {
                salaries.put(record_LastName,salaries.get(record_LastName)+record_Value);
                if (maxSalary<salaries.get(record_LastName))
                {
                    maxSalary = salaries.get(record_LastName);
                }
            }
        }

        for (Map.Entry<String,Double> x : salaries.entrySet())
        {
            if (x.getValue().equals(maxSalary))
            {
                System.out.println(x.getKey());
            }
        }

        fileReader.close();
    }
}

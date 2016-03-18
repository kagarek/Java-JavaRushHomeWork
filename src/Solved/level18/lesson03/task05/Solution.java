package solved.level18.lesson03.task05;

/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        ArrayList<Integer> bytesArray = new ArrayList<Integer>();

        //read file and fill only new byte-codes to ArrayList
        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            if (!bytesArray.contains(data))
            {
                bytesArray.add(data);
            }
        }

        //sort ArrayList
        for (int i = 0; i < bytesArray.size(); i++)
        {
            for (int j = i+1; j < bytesArray.size(); j++)
            {
                if (bytesArray.get(i) > bytesArray.get(j))
                {
                    int tmp = bytesArray.get(j);
                    bytesArray.set(j, bytesArray.get(i));
                    bytesArray.set(i, tmp);
                }
            }

        }

        String result= "";

        for (Integer x : bytesArray)
            result = result + x + " ";

        System.out.print(result.substring(0, result.length() - 1));

        reader.close();
        inputStream.close();
    }
}

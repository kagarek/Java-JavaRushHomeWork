package solved.level18.lesson03.task03;

/* Самые частые байты
Ввести с консоли имя файла
Найти байты, которые чаше всех встречаются в файле
Вывести их на экран через пробел.
Закрыть поток ввода-вывода
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFile = reader.readLine();
        FileInputStream inputStream = new FileInputStream(inputFile);
        Map<Integer,Integer> bytesArray = new HashMap<Integer, Integer>();

        while (inputStream.available() > 0)
        {
            //читаем байты
            int data = inputStream.read();

            //проверяем, если байта еще нет в HashMap - то добавляем байт в HashMap
            if (!bytesArray.containsKey(data))
            {
                bytesArray.put(data, 1);
            }
            //иначе увеличиваем существующее количество на 1
            else
            {
                bytesArray.put(data, bytesArray.get(data)+1);
            }
        }


        //дальше код по выводу самого часто встречающегося байта
        int maxValue = 0;

        for (Map.Entry<Integer,Integer> x : bytesArray.entrySet())
        {
            if (x.getValue() > maxValue) maxValue = x.getValue();
        }

        String outResult="";

        for (Map.Entry<Integer,Integer> x : bytesArray.entrySet())
        {
            if (x.getValue() == maxValue) outResult = outResult + x.getKey()+" ";
        }

        System.out.print(outResult.substring(0,outResult.length()-1));

        reader.close();
        inputStream.close();
    }
}

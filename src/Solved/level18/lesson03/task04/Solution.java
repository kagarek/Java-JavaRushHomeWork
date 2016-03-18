package solved.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байты, которые встречаются в файле меньше всего раз.
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputFile = reader.readLine();
        FileInputStream inputStream = new FileInputStream(inputFile);
        Map<Integer,Integer> bytesArray = new HashMap<Integer, Integer>();

        while (inputStream.available() > 0)
        {
            int data = inputStream.read();

            if (!bytesArray.containsKey(data))
            {
                bytesArray.put(data, 1);
            }
            else
            {
                bytesArray.put(data, bytesArray.get(data)+1);
            }
        }

        int minValue = 0;
        int i=1;

        for (Map.Entry<Integer,Integer> x : bytesArray.entrySet())
        {
            if (i==1)
            {
                minValue = x.getValue();
                i=0;
            }
            if (x.getValue() < minValue) minValue = x.getValue();
        }

        String outResult="";

        for (Map.Entry<Integer,Integer> x : bytesArray.entrySet())
        {
            if (x.getValue() == minValue) outResult = outResult + x.getKey()+" ";
        }

        System.out.print(outResult.substring(0,outResult.length()-1));

        reader.close();
        inputStream.close();

    }
}

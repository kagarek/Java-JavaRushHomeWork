package solved.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        FileInputStream fileInputStream = new FileInputStream(args[0]);
        Map<Integer,Integer> symbolsMap = new HashMap<Integer, Integer>();

        while (fileInputStream.available() > 0)
        {
            int symbolASCII = fileInputStream.read();

            if (!symbolsMap.containsKey(symbolASCII))
                symbolsMap.put(symbolASCII,1);
            else
                symbolsMap.put(symbolASCII,symbolsMap.get(symbolASCII)+1);
        }

        fileInputStream.close();

        ArrayList<Integer> keysArray = new ArrayList<Integer>(symbolsMap.keySet());

        for (int i = 0; i< keysArray.size(); i++)
        {
            for (int j = i+1; j<keysArray.size();j++)
            {
                if (keysArray.get(i) > keysArray.get(j))
                {
                    int tmp = keysArray.get(j);
                    keysArray.set(j, keysArray.get(i));
                    keysArray.set(i,tmp);
                }
            }
        }

        for (int i = 0; i < keysArray.size(); i++)
        {
            char symbol = (char) ((int) keysArray.get(i));
            int count = symbolsMap.get(keysArray.get(i));

            System.out.println(symbol + " " + count);
        }


    }
}

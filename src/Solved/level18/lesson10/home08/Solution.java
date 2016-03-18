package solved.level18.lesson10.home08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Не забудьте закрыть все потоки
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String filename;

        while (!(filename=reader.readLine()).equals("exit"))
        {
            new ReadThread(filename).start();
        }

        reader.close();
    }

    public static class ReadThread extends Thread {
        public ReadThread(String fileName) throws IOException
        {
            //implement constructor body
            ReadFileAndFindSymbol(fileName);
        }

        // implement file reading here - реализуйте чтение из файла тут

        public void ReadFileAndFindSymbol(String filename) throws IOException
        {

            FileInputStream fileInputStream = new FileInputStream(filename);

            Map<Integer,Integer> bytesArray = new HashMap<Integer, Integer>();

            while (fileInputStream.available() > 0)
            {
                int data = fileInputStream.read();

                if (!bytesArray.containsKey(data))
                {
                    bytesArray.put(data, 1);
                }
                else
                {
                    bytesArray.put(data, bytesArray.get(data)+1);
                }
            }

            int maxValue = 0;

            for (Map.Entry<Integer,Integer> x : bytesArray.entrySet())
            {
                if (x.getValue() > maxValue) maxValue = x.getValue();
            }

            for (Map.Entry<Integer,Integer> x : bytesArray.entrySet())
            {
                if (x.getValue() == maxValue) resultMap.put(filename,x.getKey());
            }

            fileInputStream.close();
        }
    }
}

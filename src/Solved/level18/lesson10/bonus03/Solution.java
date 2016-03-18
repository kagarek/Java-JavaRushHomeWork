package solved.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        ArrayList<String> fileLines = new ArrayList<String>();

        if (args[0].equals("-u"))
        {
            String line = "";
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line=fileReader.readLine())!=null)
            {
                int currentID = Integer.parseInt(line.substring(0,8).replace(" ",""));

                if (currentID==Integer.parseInt(args[1]))
                {
                    line = AddRemoveSpaces(args[1], 8)  +
                           AddRemoveSpaces(args[2], 30) +
                           AddRemoveSpaces(args[3], 8)  +
                           AddRemoveSpaces(args[4], 4);
                }

                fileLines.add(line);
            }

            fileReader.close();

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            for (int i = 0; i < fileLines.size(); i++)
            {
                byte[] buffer = fileLines.get(i).getBytes();
                fileOutputStream.write(buffer,0,buffer.length);
                //fileOutputStream.write(10);
                fileOutputStream.write(13);
            }

            fileOutputStream.close();
        }

        if (args[0].equals("-d"))
        {
            String line = "";
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line=fileReader.readLine())!=null)
            {
                int currentID = Integer.parseInt(line.substring(0,8).replace(" ",""));

                if (currentID!=Integer.parseInt(args[1]))
                {
                    fileLines.add(line);
                }
            }

            fileReader.close();

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            for (int i = 0; i < fileLines.size(); i++)
            {
                byte[] buffer = fileLines.get(i).getBytes();
                fileOutputStream.write(buffer,0,buffer.length);
                //fileOutputStream.write(10);
                fileOutputStream.write(13);
            }

            fileOutputStream.close();
        }
    }

    public static String AddRemoveSpaces(String string, int maxSymbols)
    {
        if (string.length() >= maxSymbols)
        {
            string = string.substring(0,maxSymbols);
        }
        else
        {
            for (int i = string.length(); i < maxSymbols; i++)
            {
                string = string + " ";
            }
        }

        return string;
    }
}

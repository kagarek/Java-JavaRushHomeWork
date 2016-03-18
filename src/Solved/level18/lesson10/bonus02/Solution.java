package solved.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {

        if (args[0].equals("-c"))
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();

            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            String line = "";
            int maxID = 0;

            while ((line=fileReader.readLine())!=null)
            {
                int currentID = Integer.parseInt(line.substring(0,8).replace(" ",""));
                if (currentID>maxID) maxID = currentID;
            }

            maxID++;

            FileInputStream fileInputStream = new FileInputStream(fileName);

            byte[] buffer1 = new byte[fileInputStream.available()];
            int count = fileInputStream.read(buffer1);

            fileInputStream.close();

            //prepare string for writing

            String result =
                    AddRemoveSpaces(maxID+"",8)+
                    AddRemoveSpaces(args[1],30)+
                    AddRemoveSpaces(args[2], 8)+
                    AddRemoveSpaces(args[3], 4);

            byte[] buffer2 = result.getBytes();

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            fileOutputStream.write(buffer1,0,count);
            //fileOutputStream.write(10);
            //fileOutputStream.write(13);
            fileOutputStream.write(buffer2,0,buffer2.length);

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

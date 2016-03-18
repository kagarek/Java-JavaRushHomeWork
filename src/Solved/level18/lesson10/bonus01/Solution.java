package solved.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) {

        if (args[0].equals("-e"))
            EncryptFile(args[1],args[2]);

        if (args[0].equals("-d"))
            DecryptFile(args[1], args[2]);
    }

    public static void EncryptFile(String fileName, String fileOutputName)
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(fileOutputName);

            while (fileInputStream.available() > 0)
            {
                int data = fileInputStream.read() + 128;
                fileOutputStream.write(data);
            }

            fileInputStream.close();
            fileOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void DecryptFile(String fileName, String fileOutputName)
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(fileOutputName);

            while (fileInputStream.available() > 0)
            {
                int data = fileInputStream.read() - 128;
                fileOutputStream.write(data);
            }

            fileInputStream.close();
            fileOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}

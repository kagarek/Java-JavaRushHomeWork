package solved.level31.lesson06.bonus01;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {

    static {
        String[] args = new String[4];
        args[0] = "resources/level31_lesson06_bonus01/video.mov";
        args[2] = "resources/level31_lesson06_bonus01/a1.zip.001";
        args[1] = "resources/level31_lesson06_bonus01/a1.zip.002";
        args[3] = "resources/level31_lesson06_bonus01/a1.zip.003";
        //main(args);
    }

    public static void main(String[] args)
    {
        String resultFileName = args[0];
        List<String> unsortedList = new ArrayList<>();
        List<FileInputStream> list = new ArrayList<>();

        for (int i = 1; i < args.length; i++)
            unsortedList.add(args[i]);

        Collections.sort(unsortedList);

        try
        {
            for (int i = 0; i < unsortedList.size(); i++)
                list.add(new FileInputStream(unsortedList.get(i)));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try (ZipInputStream is = new ZipInputStream(new SequenceInputStream(Collections.enumeration(list))))
        {
            for(ZipEntry entry = null; (entry = is.getNextEntry()) != null; ) {
                OutputStream os = new BufferedOutputStream(new FileOutputStream(resultFileName));
                try {
                    final int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    for(int readBytes = -1; (readBytes = is.read(buffer, 0, bufferSize)) > -1; ) {
                        os.write(buffer, 0, readBytes);
                    }
                    os.flush();
                } finally {
                    os.close();
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {

    public static void main(String[] args)
    {
        String fileNamePath = args[0];
        String zipArchivePath = args[1];
        Path p = Paths.get(fileNamePath);
        Map<ZipEntry, ByteArrayOutputStream> zipArchiveContent = new HashMap<>();

        // read archve
        try (
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipArchivePath))
            )
        {

            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                if (!ze.isDirectory())
                {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = zis.read(buffer)) != -1)
                        baos.write(buffer, 0, count);
                    zipArchiveContent.put(ze, baos);
                    baos.close();
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

        // write to archve
        try (
                ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipArchivePath)))
        )
        {
            int i = 0;
            for (Map.Entry<ZipEntry,ByteArrayOutputStream> e : zipArchiveContent.entrySet()) {
                ZipEntry entry = e.getKey();
                Path entryPath = Paths.get(entry.getName());
                if (entryPath.getFileName().toString().equals(p.getFileName().toString()))
                {
                    entry = new ZipEntry("new/"+entryPath.getFileName());
                    zos.putNextEntry(entry);
                    Files.copy(p, zos);
                    i++;
                }
                else
                {
                    zos.putNextEntry(entry);
                    zos.write(e.getValue().toByteArray());
                }
                zos.closeEntry();
            }

            if (i==0)
            {
                ZipEntry entry = new ZipEntry("new/"+p.getFileName());
                zos.putNextEntry(entry);
                Files.copy(p, zos);
                zos.closeEntry();
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

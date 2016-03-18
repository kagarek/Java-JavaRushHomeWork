package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        if (root == null || root.equals("")) return null;

        List<String> resultList = new ArrayList<>();
        File file = new File(root);

        List<File> files = new ArrayList<>();

        Collections.addAll(files, file.listFiles());

        for (int i = 0; i < files.size(); i++)
        {
            if (files.get(i).isDirectory())
            {
                Collections.addAll(files,files.get(i).listFiles());
                continue;
            }
            else
            {
                resultList.add(files.get(i).getAbsolutePath());
            }
        }

        return resultList;
    }
}

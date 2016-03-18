package solved.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"

В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть все потоки ввода-вывода
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/
/*
resources/level18_lesson10_home10_input.txt.part4
resources/level18_lesson10_home10_input.txt.part1
resources/level18_lesson10_home10_input.txt.part3
resources/level18_lesson10_home10_input.txt.part2
end
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        String resultFilePath = "";
        String resultFileName = "";
        int k = 0;
        ArrayList<String> filesToAppend = new ArrayList<String>();

        //read all fileNames
        while (!(fileName=reader.readLine()).equals("end"))
        {
            if (k==0)
            {
                resultFileName = fileName.substring(0, fileName.lastIndexOf("."));
                k++;
            }

            if (!filesToAppend.contains(fileName)) filesToAppend.add(fileName);
        }

        //sort filesToAppend by partN
        for (int i = 0; i < filesToAppend.size(); i++)
        {
            for (int j = i; j < filesToAppend.size(); j++)
            {
                String stringPart1 = filesToAppend.get(i).replace("part","");
                String stringPart2 = filesToAppend.get(j).replace("part","");

                stringPart1 = stringPart1.substring(stringPart1.lastIndexOf(".") + 1, stringPart1.length());
                stringPart2 = stringPart2.substring(stringPart2.lastIndexOf(".")+1, stringPart2.length());

                int partI = Integer.parseInt(stringPart1);
                int partJ = Integer.parseInt(stringPart2);

                if (partI > partJ)
                {
                    String tmp = filesToAppend.get(i);
                    filesToAppend.set(i, filesToAppend.get(j));
                    filesToAppend.set(j, tmp);
                }
            }
        }

        //read files one after other and write to result file
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);

        for (int i = 0; i < filesToAppend.size(); i++)
        {
            fileInputStream = new FileInputStream(filesToAppend.get(i));

            byte[] buffer = new byte[fileInputStream.available()];
            int countTo = fileInputStream.read(buffer);
            fileOutputStream.write(buffer,0,countTo);
        }

        fileInputStream.close();
        fileOutputStream.close();
        reader.close();
    }
}

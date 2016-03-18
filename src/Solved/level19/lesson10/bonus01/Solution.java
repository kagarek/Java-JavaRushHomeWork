package solved.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
[Файл 1]
строка1
строка2
строка3

[Файл 2]
строка1
строка3
строка4

[Результат - список lines]
SAME строка1
REMOVED строка2
SAME строка3
ADDED строка4
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String fileName1 = consoleReader.readLine();
        String fileName2 = consoleReader.readLine();

        //String fileName1 = "resources/level19_lesson10_bonus01_file1.txt";
        //String fileName2 = "resources/level19_lesson10_bonus01_file2.txt";

        ArrayList<String> file1_Lines = new ArrayList<String>();
        ArrayList<String> file2_Lines = new ArrayList<String>();

        String line;

        BufferedReader fileReader1 = new BufferedReader(new FileReader(fileName1));
        BufferedReader fileReader2 = new BufferedReader(new FileReader(fileName2));

        while ((line=fileReader1.readLine())!=null)
        {
            file1_Lines.add(line);
        }

        while ((line=fileReader2.readLine())!=null)
        {
            file2_Lines.add(line);
        }

        //SAME	строка1	=	строка1
        //REMOVED	строка1	!=	строка1	&	строка1	!=	строка2
        //ADDED	строка1	!=	строка1	&	строка1	=	строка2

        int j=0;

        if (file1_Lines.size() <= file2_Lines.size())
        {
            for (int i = 0 ; i < file1_Lines.size(); )
            {
                if (file1_Lines.get(i).equals(file2_Lines.get(j)))
                {
                    lines.add(new LineItem(Type.SAME, file1_Lines.get(i)));
                    i++;
                    j++;
                } else
                {
                    if (!file1_Lines.get(i).equals(file2_Lines.get(j + 1)))
                    {
                        lines.add(new LineItem(Type.REMOVED, file1_Lines.get(i)));
                        i++;
                    } else
                    {
                        if (file1_Lines.get(i).equals(file2_Lines.get(j + 1)))
                        {
                            lines.add(new LineItem(Type.ADDED, file2_Lines.get(j)));
                            j++;
                        }
                    }
                }
            }

            for (int i = j; i < file2_Lines.size(); i++)
            {
                lines.add(new LineItem(Type.ADDED, file2_Lines.get(i)));
            }
        }
        else
        {
            for (int i = 0 ; i < file1_Lines.size(); )
            {
                if (j != file2_Lines.size() && file1_Lines.get(i).equals(file2_Lines.get(j)))
                {
                    lines.add(new LineItem(Type.SAME, file1_Lines.get(i)));
                    i++;
                    j++;
                } else
                {
                    if (j != file2_Lines.size() && file1_Lines.get(i + 1).equals(file2_Lines.get(j)))
                    {
                        lines.add(new LineItem(Type.REMOVED, file1_Lines.get(i)));
                        i++;
                    } else
                    {
                        if (j != file2_Lines.size() && !file1_Lines.get(i + 1).equals(file2_Lines.get(j)))
                        {
                            lines.add(new LineItem(Type.ADDED, file2_Lines.get(j)));
                            j++;
                        } else
                        {
                            lines.add(new LineItem(Type.REMOVED, file1_Lines.get(i)));
                            i++;
                        }
                    }
                }
            }
        }

        for (LineItem x : lines)
        {
            System.out.println(x.type + " " + x.line);
        }

        fileReader1.close();
        fileReader2.close();
        consoleReader.close();
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}

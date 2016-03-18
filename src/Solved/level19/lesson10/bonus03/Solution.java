package solved.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args)
    {
        try
        {
            String tag = args[0];
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = consoleReader.readLine();
            //String tag = "span";
            //String fileName = "resources/level19_lesson10_bonus03.html";
            TreeMap<Integer, String> tagIndexes = new TreeMap<>();

            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            String allFileContent = "";
            String line;

            while ((line = fileReader.readLine()) != null)
            {
                allFileContent = allFileContent + line;
            }

            addAllOpenTags(tagIndexes,allFileContent,tag);
            addAllCloseTags(tagIndexes,allFileContent, tag);

            int openIndex = 0;
            int startIndex = 0;
            int endIndex = 0;

            while (tagIndexes.containsValue("CLOSE") || tagIndexes.containsValue("OPEN"))
            {
                for (Map.Entry<Integer,String> x : tagIndexes.entrySet())
                {
                    if (x.getValue().equals("OPEN"))
                    {
                        if (openIndex == 0)
                        {
                            startIndex = x.getKey();
                        }
                        openIndex++;
                    }

                    if (x.getValue().equals("CLOSE"))
                    {
                        openIndex--;
                        if (openIndex == 0)
                        {
                            endIndex = x.getKey();
                        }
                    }

                    if (openIndex == 0 && !x.getValue().equals("PROCESSED"))
                    {
                        System.out.println(allFileContent.substring(startIndex,endIndex+tag.length()+3));
                        tagIndexes.put(startIndex, "PROCESSED");
                        tagIndexes.put(endIndex, "PROCESSED");
                        break;
                    }
                }
            }

            fileReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addAllOpenTags(TreeMap<Integer,String> tagIndexes, String str, String tag)
    {
        int i = 0;
        while (i < str.length())
        {
            int index = str.indexOf("<" + tag,i);
            if (index != -1)
            {
                tagIndexes.put(index, "OPEN");
                i = index + tag.length();
            }
            else
                break;
        }
    }

    public static void addAllCloseTags(TreeMap<Integer,String> tagIndexes, String str, String tag)
    {
        int i = 0;
        while (i < str.length())
        {
            int index = str.indexOf("</" + tag +">",i);
            if (index != -1)
            {
                tagIndexes.put(index, "CLOSE");
                i = index+tag.length();
            }
            else break;
        }
    }

}
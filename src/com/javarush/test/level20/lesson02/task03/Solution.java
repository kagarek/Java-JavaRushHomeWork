package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

/*    public static void main(String[] args)
    {
        fillInPropertiesMap();
        System.out.println(properties.toString());
    }*/

    public void fillInPropertiesMap()
    {
        //implement this method - реализуйте этот метод
        try
        {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = consoleReader.readLine();
            InputStream inputStream = new FileInputStream(fileName);
            load(inputStream);

            OutputStream outputStream = new FileOutputStream("resources/level20_lesson02_task03_output.properties");
            save(outputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод

        PrintWriter printWriter = new PrintWriter(outputStream);

        for (Map.Entry<String,String> x : properties.entrySet())
        {
            printWriter.print(x.getKey().replace(" ","\\ "));
            printWriter.print("=");
            printWriter.println(x.getValue());
        }

        printWriter.close();
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String key = "";
        String value = "";

        while ((line=reader.readLine())!=null)
        {
            if (!line.startsWith("#") && !line.startsWith("!"))
            {
                if (line.indexOf("=") != -1)
                {
                    key = line.substring(0, line.indexOf("=")).trim().replace("\\","");

                    if (!line.endsWith("\\"))
                    {
                        value = line.substring(line.indexOf("=") + 1, line.length()).trim();
                    } else
                    {
                        value = line.substring(line.indexOf("=") + 1, line.lastIndexOf("\\")).trim();

                        String nextLine;

                        while ((nextLine = reader.readLine()).endsWith("\\"))
                        {
                            value = value + " " + nextLine.substring(0, nextLine.length() - 1).trim();
                        }

                        value = value + " " + nextLine.substring(0, nextLine.length()).trim();
                    }
                }
                else
                {
                    if (line.indexOf(":") != -1)
                    {
                        key = line.substring(0, line.indexOf(":")).trim().replace("\\", "");

                        if (!line.endsWith("\\"))
                        {
                            value = line.substring(line.indexOf(":") + 1, line.length()).trim();
                        } else
                        {
                            value = line.substring(line.indexOf(":") + 1, line.lastIndexOf("\\")).trim();

                            String nextLine;

                            while ((nextLine = reader.readLine()).endsWith("\\"))
                            {
                                value = value + " " + nextLine.substring(0, nextLine.length() - 1).trim();
                            }

                            value = value + " " + nextLine.substring(0, nextLine.length()).trim();
                        }
                    }
                }

                properties.put(key, value);

            }
        }
    }
}
package solved.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        reader.close();
        //String url = "javarush.ru/alpha/index.html?lvl=15&??view&&&name=Aobjmigo&obj=3.14&name=&obj=djsdcd&&?oobj=3.0";

        String stringParameters = "";
        String singleParameter = "";

        ArrayList<String> objValues = new ArrayList<String>();

        url = url.substring(url.indexOf("?")+1,url.length()).replace("?", "");

        String[] parameters = url.split("&");

        for (String x : parameters)
        {
            if (!x.equals(""))
            {
                if (x.indexOf("=") != -1)
                {
                    singleParameter = x.substring(0, x.indexOf("="));
                    stringParameters = stringParameters + singleParameter + " ";
                    if (singleParameter.equals("obj"))
                    {
                        objValues.add(x.substring(x.indexOf("=")+1, x.length()));
                    }
                } else
                {
                    singleParameter = x;
                    stringParameters = stringParameters + singleParameter + " ";
                }
            }
        }

        System.out.println(stringParameters.substring(0,stringParameters.length()-1));

        for (String x : objValues)
        {
            if (!x.equals(""))
            {
                try
                {
                    alert((double) Double.parseDouble(x));
                }
                catch (Exception e)
                {
                    alert(x);
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}

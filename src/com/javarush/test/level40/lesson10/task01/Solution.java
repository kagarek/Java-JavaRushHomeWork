package com.javarush.test.level40.lesson10.task01;

/* Работа с датами
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        DateFormat df;
        Date d;

        try {
            if (date.indexOf(" ") > 0) {
                df = new SimpleDateFormat("dd.m.yyyy HH:mm:ss");
                d = df.parse(date);
                System.out.println("День: "+new SimpleDateFormat("d").format(d));//День: 21
                System.out.println("День недели: "+new SimpleDateFormat("F").format(d));//День недели: 2
                System.out.println("День месяца: "+new SimpleDateFormat("d").format(d));//День месяца: 21
                System.out.println("День года: "+new SimpleDateFormat("").format(d));//День года: 111
                System.out.println("Неделя месяца: "+new SimpleDateFormat("W").format(d));//Неделя месяца: 4
                System.out.println("Неделя года: "+new SimpleDateFormat("w").format(d));//Неделя года: 17
                System.out.println("Месяц: "+new SimpleDateFormat("M").format(d));//Месяц: 3
                System.out.println("Год: "+new SimpleDateFormat("yyyy").format(d));//Год: 2014
                System.out.println("Эра: "+new SimpleDateFormat("G").format(d));//Эра: 1
                System.out.println("AM или PM: "+new SimpleDateFormat("a").format(d));//AM или PM: 1
                System.out.println("Часы: "+new SimpleDateFormat("h").format(d));//Часы: 3
                System.out.println("Часы дня: "+new SimpleDateFormat("H").format(d));//Часы дня: 15
                System.out.println("Минуты: "+new SimpleDateFormat("m").format(d));//Минуты: 56
                System.out.println("Секунды: "+new SimpleDateFormat("s").format(d));//Секунды: 45
            }
            else
                if (date.indexOf(".") > 0) {
                    df = new SimpleDateFormat("dd.m.yyyy");
                    d = df.parse(date);
                }
                else
                    if (date.indexOf(":") > 0) {
                        df = new SimpleDateFormat("HH:mm:ss");
                        d = df.parse(date);
                    }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

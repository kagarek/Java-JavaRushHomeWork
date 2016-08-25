package com.javarush.test.level40.lesson10.task02;

/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        DateTime dt;
        DateTimeFormatter formatter;
        if (date.indexOf(" ") > 0) {
            formatter = DateTimeFormat.forPattern("dd.M.yyyy HH:mm:ss");
            dt = formatter.parseDateTime(date);
            System.out.println("День: "+dt.dayOfMonth().get());//День: 21
            System.out.println("День недели: "+dt.dayOfWeek().get());//День недели: 2
            System.out.println("День месяца: "+dt.dayOfMonth().get());//День месяца: 21
            System.out.println("День года: "+dt.dayOfYear().get());//День года: 111
            System.out.println("Неделя месяца: "+ "no data");//Неделя месяца: 4
            System.out.println("Неделя года: "+dt.weekOfWeekyear().get());//Неделя года: 17
            System.out.println("Месяц: "+dt.monthOfYear().get());//Месяц: 3
            System.out.println("Год: "+dt.year().get());//Год: 2014
            System.out.println("Эра: "+dt.era().get());//Эра: 1
            System.out.println("AM или PM: "+ "no data");//AM или PM: 1
            System.out.println("Часы: "+(dt.hourOfDay().get()-12));//Часы: 3
            System.out.println("Часы дня: "+dt.hourOfDay().get());//Часы дня: 15
            System.out.println("Минуты: "+dt.minuteOfHour().get());//Минуты: 56
            System.out.println("Секунды: "+dt.secondOfMinute().get());//Секунды: 45
        }
        else
        if (date.indexOf(".") > 0) {
            formatter = DateTimeFormat.forPattern("dd.M.yyyy");
            dt = formatter.parseDateTime(date);

        }
        else
        if (date.indexOf(":") > 0) {
            formatter = DateTimeFormat.forPattern("HH:mm:ss");
            dt = formatter.parseDateTime(date);

        }
    }
}

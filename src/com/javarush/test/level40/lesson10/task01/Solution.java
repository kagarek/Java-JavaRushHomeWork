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
import java.util.Calendar;
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
        Calendar calendar = Calendar.getInstance();
        try {
            if (date.indexOf(" ") > 0) {
                df = new SimpleDateFormat("dd.M.yyyy HH:mm:ss");
                d = df.parse(date);
                calendar.setTime(d);
                System.out.println("День: "+calendar.get(Calendar.DATE));//День: 21
                System.out.println("День недели: "+calendar.get(Calendar.DAY_OF_WEEK));//День недели: 2
                System.out.println("День месяца: "+calendar.get(Calendar.DAY_OF_MONTH));//День месяца: 21
                System.out.println("День года: "+calendar.get(Calendar.DAY_OF_YEAR));//День года: 111
                System.out.println("Неделя месяца: "+calendar.get(Calendar.WEEK_OF_MONTH));//Неделя месяца: 4
                System.out.println("Неделя года: "+calendar.get(Calendar.WEEK_OF_YEAR));//Неделя года: 17
                System.out.println("Месяц: "+calendar.get(Calendar.MONTH));//Месяц: 3
                System.out.println("Год: "+calendar.get(Calendar.YEAR));//Год: 2014
                System.out.println("Эра: "+calendar.get(Calendar.ERA));//Эра: 1
                System.out.println("AM или PM: "+calendar.get(Calendar.AM_PM));//AM или PM: 1
                System.out.println("Часы: "+calendar.get(Calendar.HOUR));//Часы: 3
                System.out.println("Часы дня: "+calendar.get(Calendar.HOUR_OF_DAY));//Часы дня: 15
                System.out.println("Минуты: "+calendar.get(Calendar.MINUTE));//Минуты: 56
                System.out.println("Секунды: "+calendar.get(Calendar.SECOND));//Секунды: 45
            }
            else
                if (date.indexOf(".") > 0) {
                    df = new SimpleDateFormat("dd.M.yyyy");
                    d = df.parse(date);
                    calendar.setTime(d);
                    System.out.println("День: "+calendar.get(Calendar.DATE));//День: 21
                    System.out.println("День недели: "+calendar.get(Calendar.DAY_OF_WEEK));//День недели: 2
                    System.out.println("День месяца: "+calendar.get(Calendar.DAY_OF_MONTH));//День месяца: 21
                    System.out.println("День года: "+calendar.get(Calendar.DAY_OF_YEAR));//День года: 111
                    System.out.println("Неделя месяца: "+calendar.get(Calendar.WEEK_OF_MONTH));//Неделя месяца: 4
                    System.out.println("Неделя года: "+calendar.get(Calendar.WEEK_OF_YEAR));//Неделя года: 17
                    System.out.println("Месяц: "+calendar.get(Calendar.MONTH));//Месяц: 3
                    System.out.println("Год: "+calendar.get(Calendar.YEAR));//Год: 2014
                    System.out.println("Эра: "+calendar.get(Calendar.ERA));//Эра: 1
                }
                else
                    if (date.indexOf(":") > 0) {
                        df = new SimpleDateFormat("HH:mm:ss");
                        d = df.parse(date);
                        calendar.setTime(d);
                        System.out.println("AM или PM: "+calendar.get(Calendar.AM_PM));//AM или PM: 1
                        System.out.println("Часы: "+calendar.get(Calendar.HOUR));//Часы: 3
                        System.out.println("Часы дня: "+calendar.get(Calendar.HOUR_OF_DAY));//Часы дня: 15
                        System.out.println("Минуты: "+calendar.get(Calendar.MINUTE));//Минуты: 56
                        System.out.println("Секунды: "+calendar.get(Calendar.SECOND));//Секунды: 45
                    }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

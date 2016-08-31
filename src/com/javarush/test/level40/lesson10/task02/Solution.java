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
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        try {
            DateTime dt = null;
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.M.yyyy HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.M.yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");

            boolean containsDate = false;
            boolean containsTime = false;

            if (date.contains(" ")) {
                containsDate = true;
                containsTime = true;
                dt = dateTimeFormatter.parseDateTime(date);
            } else {
                if (date.contains(".")) {
                    containsDate = true;
                    dt = dateFormatter.parseDateTime(date);
                } else {
                    if (date.contains(":")) {
                        containsTime = true;
                        dt = timeFormatter.parseDateTime(date);
                    }
                }
            }

            DateTime minYearDate = dt.dayOfYear().withMinimumValue();
            DateTime minMonthDate = dt.dayOfMonth().withMinimumValue();
            int weekDis = (minYearDate.getWeekyear() == dt.getYear()) ? 0 : 1;
            int weekOfYear = dt.getWeekOfWeekyear() + weekDis;
            if (weekOfYear > 52)
                weekOfYear = 1;
            int weekOfMonth = dt.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
            if (minMonthDate.getWeekOfWeekyear() >= dt.getWeekOfWeekyear())
                weekOfMonth = dt.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
            if (dt.getMonthOfYear() == 1)
                weekOfMonth = weekOfYear;

            if (containsDate) {
                System.out.println("День: " + dt.getDayOfMonth());
                System.out.println("День недели: " + dt.getDayOfWeek());
                System.out.println("День месяца: " + dt.getDayOfMonth());
                System.out.println("День года: " + dt.getDayOfYear());
                System.out.println("Неделя месяца: " + weekOfMonth);
                System.out.println("Неделя года: " + weekOfYear);
                System.out.println("Месяц: " + dt.getMonthOfYear());
                System.out.println("Год: " + dt.getYear());
                System.out.println("Эра: " + dt.getEra());
            }

            if (containsTime) {
                System.out.println("AM или PM: " + ((dt.getHourOfDay() >= 12) ? 1 : 0));
                System.out.println("Часы: " + (dt.getHourOfDay() % 12));
                System.out.println("Часы дня: " + dt.getHourOfDay());
                System.out.println("Минуты: " + dt.getMinuteOfHour());
                System.out.println("Секунды: " + dt.getSecondOfMinute());
            }
        }
        catch (Exception e){}
    }
}

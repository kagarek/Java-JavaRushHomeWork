package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("./src/com/javarush/test/level39/lesson09/big01/logs"));
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        Date after = null;
//        Date before = null;
//        try
//        {
//            after = df.parse("01.01.2013");
//            before = df.parse("31.12.2013");
//        }
//        catch (ParseException e)
//        {
//            e.printStackTrace();
//        }

        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));

        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between\n" +
                "\"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}

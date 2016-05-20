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
        Date after = null;
        Date before = null;
        try
        {
            after = df.parse("01.01.2013");
            before = df.parse("31.12.2013");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        System.out.println(logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko",Event.WRITE_MESSAGE,null,null));
        System.out.println(logParser.getDatesWhenSomethingFailed(null,null));
        System.out.println(logParser.getDatesWhenErrorHappened(null,null));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko",null,null));
        System.out.println(logParser.getDateWhenUserSolvedTask("Vasya Pupkin",18,null,null));
        System.out.println(logParser.getDateWhenUserDoneTask("1Vasya Pupkin",15,null,null));
        System.out.println(logParser.getDatesWhenUserWroteMessage("Vasya Pupkin",null,null));
        System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko",null,null));
    }
}

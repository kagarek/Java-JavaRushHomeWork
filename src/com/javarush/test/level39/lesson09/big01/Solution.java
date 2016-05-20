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

        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null,null));
        System.out.println(logParser.getLoggedUsers(null,null));
        System.out.println(logParser.getWroteMessageUsers(null,null));
        System.out.println(logParser.getDownloadedPluginUsers(null,null));
        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko",null,null));
        System.out.println(logParser.getDoneTaskUsers(null,null));
        System.out.println(logParser.getDoneTaskUsers(null,null,15));
        System.out.println(logParser.getSolvedTaskUsers(null,null));
        System.out.println(logParser.getSolvedTaskUsers(null,null,1));
        System.out.println(logParser.getUsersForIP("127.0.0.1",null,null));
    }
}

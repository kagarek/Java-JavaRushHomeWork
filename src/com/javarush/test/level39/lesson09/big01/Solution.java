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
        try
        {
            after = df.parse("01.01.2016");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Date before = new Date();

        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println(logParser.getNumberOfUniqueIPs(after, null));
        System.out.println(logParser.getNumberOfUniqueIPs(null, before));
        System.out.println(logParser.getNumberOfUniqueIPs(after, before));

        System.out.println(logParser.getUniqueIPs(null, null));
        System.out.println(logParser.getUniqueIPs(after, null));
        System.out.println(logParser.getUniqueIPs(null, before));
        System.out.println(logParser.getUniqueIPs(after, before));

        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK,null, null));
        System.out.println(logParser.getIPsForEvent(Event.DOWNLOAD_PLUGIN,after, null));
        System.out.println(logParser.getIPsForEvent(Event.LOGIN,null, before));
        System.out.println(logParser.getIPsForEvent(Event.SOLVE_TASK,after, before));

        System.out.println(logParser.getIPsForStatus(Status.ERROR,null, null));
        System.out.println(logParser.getIPsForStatus(Status.FAILED,after, null));
        System.out.println(logParser.getIPsForStatus(Status.OK,null, before));
        System.out.println(logParser.getIPsForStatus(Status.OK,after, before));

        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko",null, null));
        System.out.println(logParser.getIPsForUser("Amigo",after, null));
        System.out.println(logParser.getIPsForUser("Eduar Petrovi Morozk",null, before));
        System.out.println(logParser.getIPsForUser("Vasya Pupkin",after, before));
    }
}

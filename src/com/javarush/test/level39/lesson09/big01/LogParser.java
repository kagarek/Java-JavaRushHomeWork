package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LogParser implements IPQuery {
    private ArrayList<LogEntity> logRecords = new ArrayList<>();

    class LogEntity {
        String ip;
        String user;
        Date date;
        Event event;
        Status status;

        public LogEntity(String ip, String user, String date, String event, String status)
        {
            this.ip = ip;
            this.user = user;
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            try
            {
                this.date = sdf.parse(date);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            this.event = event.indexOf(" ") > 0 ? Event.valueOf(event.substring(0,event.indexOf(" "))) : Event.valueOf(event);
            this.status = Status.valueOf(status);
        }

        public String getIp() { return ip; }
        public String getUser() { return user; }
        public Date getDate() { return date; }
        public Event getEvent() { return event; }
        public Status getStatus() {return status; }
    }

    public LogParser(Path logDir) {
        try
        {
            File logFolder = new File(logDir.toString());
            File[] logFileList = logFolder.listFiles();

            for (File aLogFileList : logFileList)
            {
                if (aLogFileList.isFile() && aLogFileList.toString().endsWith(".log"))
                {
                    try (BufferedReader br = new BufferedReader(new FileReader(aLogFileList));)
                    {
                        String s;
                        while ((s = br.readLine()) != null)
                        {
                            String[] logString = s.split("\t");
                            String ip = logString[0];
                            String user = logString[1];
                            String dateString = logString[2];
                            String eventString = logString[3];
                            String statusString = logString[4];
                            logRecords.add(new LogEntity(ip, user, dateString, eventString, statusString));
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (Exception e) {e.printStackTrace();}
    }


    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();

        for (LogEntity logEntity : logRecords)
        {
            if (!uniqueIPs.contains(logEntity.getIp()))
            {
                if (after == null) {
                    if (before == null) uniqueIPs.add(logEntity.getIp());
                    else if (logEntity.getDate().before(before)) uniqueIPs.add(logEntity.getIp());
                }
                else
                    if (before == null) {
                        if (logEntity.getDate().after(after)) uniqueIPs.add(logEntity.getIp());
                    }
                    else if (logEntity.getDate().after(after) && logEntity.getDate().before(before)) uniqueIPs.add(logEntity.getIp());
            }
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {

        Set<String> IPsForUser = new HashSet<>();

        for (LogEntity logEntity : logRecords)
        {
            if (!IPsForUser.contains(logEntity.getIp()) && logEntity.getUser().equalsIgnoreCase(user))
            {
                if (after == null) {
                    if (before == null) IPsForUser.add(logEntity.getIp());
                    else if (logEntity.getDate().before(before)) IPsForUser.add(logEntity.getIp());
                }
                else
                if (before == null) {
                    if (logEntity.getDate().after(after)) IPsForUser.add(logEntity.getIp());
                }
                else if (logEntity.getDate().after(after) && logEntity.getDate().before(before)) IPsForUser.add(logEntity.getIp());
            }
        }
        return IPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {

        Set<String> IPsForEvent = new HashSet<>();

        for (LogEntity logEntity : logRecords)
        {
            if (!IPsForEvent.contains(logEntity.getIp()) && event.equals(logEntity.getEvent()))
            {
                if (after == null) {
                    if (before == null) IPsForEvent.add(logEntity.getIp());
                    else if (logEntity.getDate().before(before)) IPsForEvent.add(logEntity.getIp());
                }
                else
                if (before == null) {
                    if (logEntity.getDate().after(after)) IPsForEvent.add(logEntity.getIp());
                }
                else if (logEntity.getDate().after(after) && logEntity.getDate().before(before)) IPsForEvent.add(logEntity.getIp());
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {

        Set<String> IPsForStatus = new HashSet<>();

        for (LogEntity logEntity : logRecords)
        {
            if (!IPsForStatus.contains(logEntity.getIp()) && status.equals(logEntity.getStatus()))
            {
                if (after == null) {
                    if (before == null) IPsForStatus.add(logEntity.getIp());
                    else if (logEntity.getDate().before(before)) IPsForStatus.add(logEntity.getIp());
                }
                else
                if (before == null) {
                    if (logEntity.getDate().after(after)) IPsForStatus.add(logEntity.getIp());
                }
                else if (logEntity.getDate().after(after) && logEntity.getDate().before(before)) IPsForStatus.add(logEntity.getIp());
            }
        }

        return IPsForStatus;
    }
}

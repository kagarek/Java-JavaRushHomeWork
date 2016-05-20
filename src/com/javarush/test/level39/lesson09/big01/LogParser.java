package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.IPQuery;
import com.javarush.test.level39.lesson09.big01.query.UserQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LogParser implements IPQuery,UserQuery {
    private ArrayList<LogEntity> logRecords = new ArrayList<>();

    private class LogEntity {
        String ip;
        String user;
        Date date;
        Event event;
        int task;
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
            this.task = event.indexOf(" ") > 0 ? Integer.parseInt(event.substring(event.indexOf(" ")+1,event.length())) : -1;
            this.status = Status.valueOf(status);
        }

        public String getIp() { return ip; }
        public String getUser() { return user; }
        public Date getDate() { return date; }
        public Event getEvent() { return event; }
        public int getTask() { return task; }
        public Status getStatus() {return status; }
    }

    public LogParser(Path logDir) {
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
                        String ip = logString[0].trim();
                        String user = logString[1].trim();
                        String dateString = logString[2].trim();
                        String eventString = logString[3].trim();
                        String statusString = logString[4].trim();
                        logRecords.add(new LogEntity(ip, user, dateString, eventString, statusString));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean logEntityFitsPeriod(LogEntity logEntity, Date after, Date before) {
        if (after == null) {
            if (before == null) {
                return true;
            }
            else {
                if (logEntity.getDate().getTime() <= before.getTime()) {
                    return true;
                }
            }
        }
        else {
            if (before == null) {
                if (logEntity.getDate().getTime() >= after.getTime()) {
                    return true;
                }
            } else {
                return logEntity.getDate().getTime() >= after.getTime() && logEntity.getDate().getTime() <= before.getTime();
            }
        }

        return false;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!uniqueIPs.contains(logEntity.getIp()) && logEntityFitsPeriod(logEntity,after,before))
                uniqueIPs.add(logEntity.getIp());

        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {

        Set<String> IPsForUser = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!IPsForUser.contains(logEntity.getIp()) && logEntity.getUser().equalsIgnoreCase(user) && logEntityFitsPeriod(logEntity,after,before))
                IPsForUser.add(logEntity.getIp());

        return IPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {

        Set<String> IPsForEvent = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!IPsForEvent.contains(logEntity.getIp()) && event.equals(logEntity.getEvent()) && logEntityFitsPeriod(logEntity,after,before))
                IPsForEvent.add(logEntity.getIp());

        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {

        Set<String> IPsForStatus = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!IPsForStatus.contains(logEntity.getIp()) && status.equals(logEntity.getStatus()) && logEntityFitsPeriod(logEntity,after,before))
                IPsForStatus.add(logEntity.getIp());

        return IPsForStatus;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> allUsers = new HashSet<>();
        for (LogEntity logEntity : logRecords)
            if (!allUsers.contains(logEntity.getUser()))
                allUsers.add(logEntity.getUser());
        return allUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> uniqueUsers = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!uniqueUsers.contains(logEntity.getUser()) && logEntityFitsPeriod(logEntity,after,before))
                uniqueUsers.add(logEntity.getUser());

        return uniqueUsers.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> userEvents = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!userEvents.contains(logEntity.getEvent()) && user.equalsIgnoreCase(logEntity.getUser())
                    && logEntityFitsPeriod(logEntity,after,before))
                userEvents.add(logEntity.getEvent());

        return userEvents.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {

        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && ip.equals(logEntity.getIp())
                    && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {

        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && logEntity.getEvent().equals(Event.LOGIN)
                    && logEntity.getStatus().equals(Status.OK) && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && logEntity.getEvent().equals(Event.DOWNLOAD_PLUGIN)
                    && logEntity.getStatus().equals(Status.OK) && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && logEntity.getEvent().equals(Event.WRITE_MESSAGE)
                    && logEntity.getStatus().equals(Status.OK) && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && logEntity.getEvent().equals(Event.SOLVE_TASK)
                     // && logEntity.getStatus().equals(Status.OK)
                     && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && logEntity.getEvent().equals(Event.SOLVE_TASK)
                    //&& logEntity.getStatus().equals(Status.OK)
                    && logEntity.getTask() == task && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && logEntity.getEvent().equals(Event.DONE_TASK)
                    //&& logEntity.getStatus().equals(Status.OK)
                    && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!users.contains(logEntity.getUser()) && logEntity.getEvent().equals(Event.DONE_TASK)
                    //&& logEntity.getStatus().equals(Status.OK)
                    && logEntity.getTask() == task && logEntityFitsPeriod(logEntity,after,before))
                users.add(logEntity.getUser());

        return users;
    }
}

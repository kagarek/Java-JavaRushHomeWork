package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery,UserQuery,DateQuery,EventQuery,QLQuery {
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

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && user.equalsIgnoreCase(logEntity.getUser())
                    && event.equals(logEntity.getEvent())
                    && Status.OK.equals(logEntity.getStatus())
                )
                dates.add(logEntity.getDate());

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && Status.FAILED.equals(logEntity.getStatus())
                )
                dates.add(logEntity.getDate());

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && Status.ERROR.equals(logEntity.getStatus())
                )
                dates.add(logEntity.getDate());

        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && user.equalsIgnoreCase(logEntity.getUser())
                    && Event.LOGIN.equals(logEntity.getEvent())
                    && Status.OK.equals(logEntity.getStatus())
                )
                dates.add(logEntity.getDate());

        if (dates.isEmpty()) return null;

        return dates.iterator().next();
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && user.equalsIgnoreCase(logEntity.getUser())
                    && Event.SOLVE_TASK.equals(logEntity.getEvent())
                    && task == logEntity.getTask()
                    //&& Status.OK.equals(logEntity.getStatus())
                    )
                dates.add(logEntity.getDate());

        if (dates.isEmpty()) return null;

        return dates.iterator().next();
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && user.equalsIgnoreCase(logEntity.getUser())
                    && Event.DONE_TASK.equals(logEntity.getEvent())
                    && task == logEntity.getTask()
                    && Status.OK.equals(logEntity.getStatus())
                )
                dates.add(logEntity.getDate());

        if (dates.isEmpty()) return null;

        return dates.iterator().next();
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && user.equalsIgnoreCase(logEntity.getUser())
                    && Event.WRITE_MESSAGE.equals(logEntity.getEvent())
                    && Status.OK.equals(logEntity.getStatus())
                )
                dates.add(logEntity.getDate());

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> dates = new HashSet<>();

        for (LogEntity logEntity : logRecords)
            if (!dates.contains(logEntity.getDate())
                    && logEntityFitsPeriod(logEntity,after,before)
                    && user.equalsIgnoreCase(logEntity.getUser())
                    && Event.DOWNLOAD_PLUGIN.equals(logEntity.getEvent())
                    && Status.OK.equals(logEntity.getStatus())
                )
                dates.add(logEntity.getDate());

        return dates;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && !events.contains(logEntity.getEvent())
                )
                events.add(logEntity.getEvent());
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && !events.contains(logEntity.getEvent())
                    && ip.equals(logEntity.getIp())
                )
                events.add(logEntity.getEvent());
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && !events.contains(logEntity.getEvent())
                    && user.equalsIgnoreCase(logEntity.getUser())
                    )
                events.add(logEntity.getEvent());

        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && !events.contains(logEntity.getEvent())
                    && Status.FAILED.equals(logEntity.getStatus())
                    )
                events.add(logEntity.getEvent());

        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && !events.contains(logEntity.getEvent())
                    && Status.ERROR.equals(logEntity.getStatus())
                )
                events.add(logEntity.getEvent());

        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int number = 0;

        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && task == logEntity.getTask()
                    && Event.SOLVE_TASK.equals(logEntity.getEvent())

                )
                number++;

        return number;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int number = 0;

        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && task == logEntity.getTask()
                    && Event.SOLVE_TASK.equals(logEntity.getEvent())
                    && Status.OK.equals(logEntity.getStatus())
                )
                number++;

        return number;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer> map = new HashMap<>();

        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && Event.SOLVE_TASK.equals(logEntity.getEvent())
                    )
            {
                if (map.containsKey(logEntity.getTask())) {
                    map.put(logEntity.getTask(), map.get(logEntity.getTask()) + 1);
                } else {
                    map.put(logEntity.getTask(), 1);
                }
            }

        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer> map = new HashMap<>();

        for (LogEntity logEntity : logRecords)
            if (logEntityFitsPeriod(logEntity,after,before)
                    && Event.DONE_TASK.equals(logEntity.getEvent())
                )
            {
                if (map.containsKey(logEntity.getTask())) {
                    map.put(logEntity.getTask(), map.get(logEntity.getTask()) + 1);
                } else {
                    map.put(logEntity.getTask(), 1);
                }
            }

        return map;
    }

    @Override
    public Set<Object> execute(String query) {

        switch (query) {
            case "get ip" : return getUniqueIPs(null,null);
            case "get user" : return getAllUsers();
            case "get date" : return null;
            case "get event" : return getAllEvents(null,null);
            case "get status" : return null;
        }

        return null;
    }
}

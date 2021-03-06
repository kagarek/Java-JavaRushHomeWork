package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Igor_Makarychev on 26.11.2015.
 */
public class StatisticManager
{
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance()
    {
        return ourInstance;
    }

    private StatisticManager() {}

    public void register(EventDataRow data)
    {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    private class StatisticStorage
    {
        private Map<EventType, List<EventDataRow>> map;

        StatisticStorage() {
            map = new HashMap<>();
            for (EventType eventType : EventType.values())
                map.put(eventType, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data) { map.get(data.getType()).add(data); }

        public Map<EventType, List<EventDataRow>> getMap() { return map; }
    }

    public Map<Date,Double> getAdvertisementProfitByDate()
    {
        Map<Date,Double> result = new HashMap<>();
        List<EventDataRow> dataRows = statisticStorage.getMap().get(EventType.SELECTED_VIDEOS);

        for (EventDataRow e : dataRows)
        {
            VideoSelectedEventDataRow tmp = (VideoSelectedEventDataRow) e;
            Double amount = tmp.getAmount() * 1d /100;

            Calendar cal = Calendar.getInstance();
            cal.setTime(tmp.getDate());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date currentDate = cal.getTime();

            if (!result.containsKey(currentDate))
                result.put(currentDate, amount);
            else
                result.put(currentDate, result.get(currentDate)+amount);
        }

        return result;
    }

    public Map<Date, Map<String,Integer>> getCookWorkloadingByDate() {
        Map<Date,Map<String,Integer>> outerResultMap = new HashMap<>();
        List<EventDataRow> dataRows = statisticStorage.getMap().get(EventType.COOKED_ORDER);

        for (EventDataRow e : dataRows)
        {
            CookedOrderEventDataRow tmp = (CookedOrderEventDataRow) e;
            String cookName = tmp.getCookName();
            int cookTime = tmp.getTime();

            Calendar cal = Calendar.getInstance();
            cal.setTime(tmp.getDate());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date currentDate = cal.getTime();

            if (!outerResultMap.containsKey(currentDate))
            {
                Map <String, Integer> innerCookMap = new HashMap<>();
                innerCookMap.put(cookName, cookTime);
                outerResultMap.put(currentDate, innerCookMap);
            }
            else
            {
                if (!outerResultMap.get(currentDate).containsKey(cookName))
                {
                    Map <String, Integer> innerCookMap = new HashMap<>();
                    innerCookMap.put(cookName, cookTime);
                    outerResultMap.put(currentDate, innerCookMap);
                }
                else
                {
                    Map <String, Integer> innerCookMap = outerResultMap.get(currentDate);
                    innerCookMap.put(cookName, innerCookMap.get(cookName) + cookTime);
                }
            }
        }

        return outerResultMap;
    }

    public Map<Advertisement,Integer> getActiveVideoSet() {

        return null;
    }

    public List<Advertisement> getArchivedVideoSet() {

        return null;

    }
}

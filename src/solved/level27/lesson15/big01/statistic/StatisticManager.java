package solved.level27.lesson15.big01.statistic;

import solved.level27.lesson15.big01.ad.Advertisement;
import solved.level27.lesson15.big01.kitchen.Cook;
import solved.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import solved.level27.lesson15.big01.statistic.event.EventDataRow;
import solved.level27.lesson15.big01.statistic.event.EventType;
import solved.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

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

    public Map<String,Double> getAdvertisementProfitByDate()
    {
        Map<String,Double> result = new HashMap<>();
        List<EventDataRow> dataRows = statisticStorage.getMap().get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        for (EventDataRow e : dataRows)
        {
            VideoSelectedEventDataRow tmp = (VideoSelectedEventDataRow) e;
            Double amount = tmp.getAmount() * 1d /100;
            Date currentDate = tmp.getDate();
            String formattedDate = sdf.format(currentDate);

            if (!result.containsKey(formattedDate))
                result.put(formattedDate, amount);
            else
                result.put(formattedDate, result.get(formattedDate)+amount);
        }

        return result;
    }

    public Map<String, Map<String,Integer>> getCookWorkloadingByDate() {
        Map<String,Map<String,Integer>> outerResultMap = new HashMap<>();
        List<EventDataRow> dataRows = statisticStorage.getMap().get(EventType.COOKED_ORDER);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        for (EventDataRow e : dataRows)
        {
            CookedOrderEventDataRow tmp = (CookedOrderEventDataRow) e;
            String cookName = tmp.getCookName();
            int cookTime = tmp.getTime();
            Date currentDate = tmp.getDate();
            String formattedDate = sdf.format(currentDate);

            if (!outerResultMap.containsKey(formattedDate))
            {
                Map <String, Integer> innerCookMap = new HashMap<>();
                innerCookMap.put(cookName, cookTime);
                outerResultMap.put(formattedDate, innerCookMap);
            }
            else
            {
                if (!outerResultMap.get(formattedDate).containsKey(cookName))
                {
                    Map <String, Integer> innerCookMap = new HashMap<>();
                    innerCookMap.put(cookName, cookTime);
                    outerResultMap.put(formattedDate, innerCookMap);
                }
                else
                {
                    Map <String, Integer> innerCookMap = outerResultMap.get(formattedDate);
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

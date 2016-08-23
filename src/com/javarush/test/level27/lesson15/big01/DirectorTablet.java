package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor_Makarychev on 26.11.2015.
 */
public class DirectorTablet
{

    public void printAdvertisementProfit() {
        Map<Date, Double> data = StatisticManager.getInstance().getAdvertisementProfitByDate();
        double total = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        for (Map.Entry<Date,Double> e : data.entrySet())
        {
            System.out.println(sdf.format(e.getKey()) + " - " + e.getValue());
            total += e.getValue();
        }
        System.out.println("Total - " + total);
    }

    public void printCookWorkloading() {
        Map<Date,Map<String, Integer>> data = StatisticManager.getInstance().getCookWorkloadingByDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        for (Map.Entry<Date,Map<String, Integer>> outerMap : data.entrySet())
        {
            Map<String,Integer> innerData = new HashMap<>(outerMap.getValue());
            System.out.println(sdf.format(outerMap.getKey()));
            for (Map.Entry<String,Integer> innerMap : innerData.entrySet())
                System.out.println(innerMap.getKey() + " - " + (int) Math.ceil(innerMap.getValue()/60) + " min");
            System.out.println();
        }
    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }
}

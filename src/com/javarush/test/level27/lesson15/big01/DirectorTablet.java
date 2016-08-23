package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor_Makarychev on 26.11.2015.
 */
public class DirectorTablet
{

    public void printAdvertisementProfit() {
        Map<String, Double> data = StatisticManager.getInstance().getAdvertisementProfitByDate();
        double total = 0;
        for (Map.Entry<String,Double> e : data.entrySet())
        {
            System.out.println(e.getKey() + " - " + e.getValue());
            total += e.getValue();
        }
        System.out.println("Total - " + total);
    }

    public void printCookWorkloading() {
        Map<String,Map<String, Integer>> data = StatisticManager.getInstance().getCookWorkloadingByDate();
        for (Map.Entry<String,Map<String, Integer>> outerMap : data.entrySet())
        {
            Map<String,Integer> innerData = new HashMap<>(outerMap.getValue());
            System.out.println(outerMap.getKey());
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

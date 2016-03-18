package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by igormakarychev on 1/12/16.
 */
public class Solution
{
    public static void main(String[] args)
    {
/*        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 10);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);*/
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for(String s : strings)
            ids.add(shortener.getId(s));
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for(Long l : keys)
            strings.add(shortener.getString(l));
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        long i=0L;
        while (i < elementsNumber)
        {
            strings.add(Helper.generateRandomString());
            i++;
        }
        Shortener shortener = new Shortener(strategy);

        Date checkIDS_Start = new Date();
        Set<Long> ids = getIds(shortener,strings);
        Date checkIDS_End = new Date();
        Long checkIDS_diff = checkIDS_End.getTime()-checkIDS_Start.getTime();
        Helper.printMessage(String.valueOf(checkIDS_diff));

        Date checkSTRINGS_Start = new Date();
        Set<String> result = getStrings(shortener, ids);
        Date checkSTRINGS_End = new Date();
        Long checkSTRINGS_diff = checkSTRINGS_End.getTime()-checkSTRINGS_Start.getTime();
        Helper.printMessage(String.valueOf(checkSTRINGS_diff));

        if (strings.equals(result)) Helper.printMessage("Тест пройден.");
        else  Helper.printMessage("Тест не пройден.");

    }

}
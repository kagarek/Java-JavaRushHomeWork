package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Igor_Makarychev on 25.01.2016.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        Long start = new Date().getTime();
        for (String s : strings)
            ids.add(shortener.getId(s));
        Long end = new Date().getTime();
        return end - start;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        Long start = new Date().getTime();
        for (Long l : ids)
            strings.add(shortener.getString(l));
        Long end = new Date().getTime();
        return end - start;
    }

    @Test
    public void testHashMapStorage()
    {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();

        for (int i = 0; i < 10000; i++)
            origStrings.add(Helper.generateRandomString());

        long timeForGettingIds_shortener1 = getTimeForGettingIds(shortener1, origStrings, ids1);
        long timeForGettingIds_shortener2 = getTimeForGettingIds(shortener2, origStrings, ids2);

        Assert.assertTrue(timeForGettingIds_shortener1 > timeForGettingIds_shortener2);

        long timeForGettingStrings_shortener1 = getTimeForGettingStrings(shortener1, ids1, new HashSet<String>());
        long timeForGettingStrings_shortener2 = getTimeForGettingStrings(shortener2, ids2, new HashSet<String>());

        Assert.assertEquals(timeForGettingStrings_shortener1, timeForGettingStrings_shortener2, 15);
    }
}
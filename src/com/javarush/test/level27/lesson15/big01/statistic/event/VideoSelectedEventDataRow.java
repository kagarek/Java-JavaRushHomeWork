package com.javarush.test.level27.lesson15.big01.statistic.event;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Igor_Makarychev on 26.11.2015.
 */
public class VideoSelectedEventDataRow implements EventDataRow
{
    private Date currentDate;
    private List<Advertisement> optimalVideoSet;
    private long amount;
    private int totalDuration;


    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration)
    {
        this.optimalVideoSet = new ArrayList<>(optimalVideoSet);
        this.amount = amount;
        this.totalDuration = totalDuration;
        this.currentDate =  new GregorianCalendar().getTime();
    }

    @Override
    public EventType getType() { return EventType.SELECTED_VIDEOS; }

    @Override
    public Date getDate()
    {
        return currentDate;
    }

    @Override
    public int getTime()
    {
        return totalDuration;
    }

    public long getAmount() { return amount; }
}

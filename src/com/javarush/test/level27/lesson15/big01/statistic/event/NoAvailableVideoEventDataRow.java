package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Igor_Makarychev on 26.11.2015.
 */
public class NoAvailableVideoEventDataRow implements EventDataRow
{
    private Date currentDate;
    private int totalDuration;

    public NoAvailableVideoEventDataRow(int totalDuration)
    {
        this.totalDuration = totalDuration;
        this.currentDate = new GregorianCalendar().getTime();
    }

    @Override
    public EventType getType()
    {
        return EventType.NO_AVAILABLE_VIDEO;
    }

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
}

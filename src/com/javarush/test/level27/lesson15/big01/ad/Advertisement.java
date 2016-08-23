package com.javarush.test.level27.lesson15.big01.ad;

/**
 * Created by igormakarychev on 6/23/15.
 */
public class Advertisement
{
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public int getHits()
    {
        return hits;
    }

    public int getDuration() { return duration; }

    public String getName()
    {
        return name;
    }

    public long getAmountPerOneDisplaying()
    {
        return amountPerOneDisplaying;
    }

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration)
    {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits + (initialAmount % hits > 0 ? 1 : 0);
    }

    public void revalidate()
    {
        if (hits <= 0)
            throw new UnsupportedOperationException();
        else {
            hits--;
            initialAmount -= amountPerOneDisplaying;
            amountPerOneDisplaying = (hits == 0) ? amountPerOneDisplaying : (initialAmount / hits + (initialAmount % hits > 0 ? 1 : 0));
        }
    }
}

package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;

/**
 * Created by igormakarychev on 1/13/16.
 */
public class Entry implements Serializable
{
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next)
    {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() { return key; }

    public String getValue() { return value; }

    public int hashCode() { return hash; }

    public String toString() { return value.toString(); }
}

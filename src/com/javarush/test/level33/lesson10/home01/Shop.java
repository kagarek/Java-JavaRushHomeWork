package com.javarush.test.level33.lesson10.home01;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igormakarychev on 1/6/16.
 */
@XmlRootElement
public class Shop
{
    @XmlElementWrapper(name = "goods", nillable = true)
    public List<String> names;

    public int count;
    public double profit;
    public List<String> secretData;

    public Shop() {
        names = new ArrayList<>();
        secretData = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        return "Shop{" +
                "names=" + names +
                ", count=" + count +
                ", profit=" + profit +
                ", secretData=" + secretData +
                '}';
    }
}
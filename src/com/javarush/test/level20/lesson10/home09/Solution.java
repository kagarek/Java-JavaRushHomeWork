package com.javarush.test.level20.lesson10.home09;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/* Знакомство с графами
Дан ориентированный плоский граф Solution, содержащий циклы и петли.
Пример, http://edu.nstu.ru/courses/saod/images/graph1.gif
Сериализовать Solution.
Все данные должны сохранить порядок следования.
*/
public class Solution implements Serializable
{
    private static final long serialVersionUID = 123456789;
    int node;
    List<Solution> edges = new LinkedList<>();

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeInt(node);
        out.writeObject(edges);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        node = in.readInt();
        edges = (List<Solution>) in.readObject();
    }
}

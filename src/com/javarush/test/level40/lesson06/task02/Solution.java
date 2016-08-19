package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.

http://ru.wikihow.com/найти-площадь-многоугольника
https://otvet.mail.ru/question/53194356

https://habrahabr.ru/post/161237/
*/

import java.util.ArrayList;
import java.util.List;

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println("Should be true - "  + isPointInPolygon(new Point(5, 5), polygon));
        System.out.println("Should be false - " + isPointInPolygon(new Point(100, 100), polygon));

        polygon.clear();

        polygon.add(new Point(-3, -2));
        polygon.add(new Point(-1, 4));
        polygon.add(new Point(6, 1));
        polygon.add(new Point(3, 10));
        polygon.add(new Point(-4, 9));

        System.out.println("Should be true - "  + isPointInPolygon(new Point(4, 4), polygon));
        System.out.println("Should be true - "  + isPointInPolygon(new Point(0, 0), polygon));
        System.out.println("Should be true - "  + isPointInPolygon(new Point(7, 4), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        double sm1=0;
        double sm2=0;
        double sm;
        double st=0;

        for (int i = 0; i < polygon.size(); i++) {
            Point point2;
            Point point3;

            if (i == polygon.size()-1)
            {
                point2 = polygon.get(i);
                point3 = polygon.get(0);
            }
            else {
                point2 = polygon.get(i);
                point3 = polygon.get(i + 1);
            }
            sm1+=point2.x*point3.y;
            sm2+=point2.y*point3.x;
            st+=Math.abs((point.x-point3.x)*(point2.y-point3.y)-(point2.x-point3.x)*(point.y-point3.y))/2;
        }

        sm = Math.abs(sm1-sm2)/2;

        System.out.println(sm);

        return sm==st;
    }

    public static int check(Point a, Point b, Point middle)
    {
        long ax = a.x - middle.x;
        long ay = a.y - middle.y;
        long bx = b.x - middle.x;
        long by = b.y - middle.y;
        int s = Long.signum(ax * by - ay * bx);
        if (s == 0 && (ay == 0 || by == 0) && ax * bx <= 0)
            return 0;
        if (ay < 0 ^ by < 0)
        {
            if (by < 0)
                return s;
            return -s;
        }
        return 1;
    }

}

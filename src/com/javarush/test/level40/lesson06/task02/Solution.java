package com.javarush.test.level40.lesson06.task02;

/* Принадлежность точки многоугольнику
Дан многоугольник, заданный координатами своих вершин.
Ребра многоугольника не пересекаются.
Необходимо реализовать метод isPointInPolygon(Point point, List<Point> polygon), который проверит,
принадлежит ли переданная точка многоугольнику.
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

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
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

        return sm==st;
    }

}
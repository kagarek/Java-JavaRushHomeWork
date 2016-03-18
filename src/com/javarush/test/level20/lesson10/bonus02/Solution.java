package com.javarush.test.level20.lesson10.bonus02;

import java.util.ArrayList;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {

        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 2" + " result: " + String.valueOf( count == 2));

        a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 1}
        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 3" + " result: " + String.valueOf( count == 3));

        a = new byte[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0}
        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 1" + " result: " + String.valueOf( count == 1));

        a = new byte[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1}

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 3" + " result: " + String.valueOf( count == 3));

        a = new byte[][]{
                {1, 0, 0,},
                {0, 0, 0,},
                {0, 0, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 2" + " result: " + String.valueOf( count == 2));

        a = new byte[][]{
                {1, 1, 0,},
                {0, 0, 0,},
                {0, 1, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 2" + " result: " + String.valueOf( count == 2));

        a = new byte[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 1" + " result: " + String.valueOf( count == 1));

        a = new byte[][]{
                {1, 0, 1,},
                {0, 0, 1,},
                {1, 0, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 3" + " result: " + String.valueOf( count == 3));

        a = new byte[][]{
                {0, 0, 0,},
                {0, 0, 0,},
                {0, 0, 0,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 0" + " result: " + String.valueOf( count == 0));

        a = new byte[][]{
                {1, 1, 0,},
                {1, 0, 0,},
                {0, 1, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 2" + " result: " + String.valueOf(count == 2));
    }

    public static class Direction{
        int x;
        int y;

        Direction(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static ArrayList<Direction> dirs = new ArrayList<>();

    static
    {
        dirs.add(new Direction(1, 0)); //forward x  - from left to right
        dirs.add(new Direction(0, 1)); //forward y - from top to bottom
        dirs.add(new Direction(-1, 0)); //backward x - from right to left
        dirs.add(new Direction(0, -1)); //backward y - from bottom to top
    }

    public static String getNextRectangleStart_Y_X(byte[][] a)
    {
        int startY = -1;
        int startX = -1;

        // find if there is another 1 in the array
        for (int y = 0; y < a.length; y++)
        {
            startY = -1;
            startX = -1;
            boolean cycleOut = false;

            for (int x = 0; x < a[y].length; x++)
            {
                if (a[y][x] == 1)
                {
                    startY = y;
                    startX = x;
                    cycleOut = true;
                    break;
                }
            }

            if (cycleOut) break;
        }

        return startY+","+startX;
    }


    public static byte[][] processRectangle(byte[][] a, String nextCoordinates)
    {
        int y = Integer.parseInt(nextCoordinates.substring(0,nextCoordinates.indexOf(",")));
        int x = Integer.parseInt(nextCoordinates.substring(nextCoordinates.indexOf(",")+1, nextCoordinates.length()));
        int startY = y;
        int startX = x;
        int endY = y;
        int endX = x;

        for (int curDir = 0; curDir < dirs.size(); curDir++)
        {
            while (true)
            {
                if (a[y][x] == 1
                        && y + dirs.get(curDir).y <= a.length - 1
                        && x + dirs.get(curDir).x <= a[y].length -1
                        && y + dirs.get(curDir).y >= 0
                        && x + dirs.get(curDir).x >= 0)
                {
                    y = y + dirs.get(curDir).y;
                    x = x + dirs.get(curDir).x;
                }
                else
                {
                    if (a[y][x] == 0)
                    {
                        y = y - dirs.get(curDir).y;
                        x = x - dirs.get(curDir).x;
                    }

                    if (curDir == 1)
                    {
                        endY = y;
                        endX = x;
                    }
                    break;
                }
            }
        }

        endY = (endY < a.length - 1) ? endY+1 : endY;
        endX = (endX < a[endY].length - 1) ? endX+1 : endX;

        for (int y1 = startY; y1 <= endY; y1++)
            for (int x1 = startX; x1 <= endX; x1++)
                a[y1][x1] = 0;

        return a;
    }

    public static int getRectangleCount(byte[][] a)
    {
        byte[][] tmpA = a;
        int count = 0;

        while (true)
        {
            String nextCoordinates = getNextRectangleStart_Y_X(tmpA);

            if (nextCoordinates.equals("-1,-1")) return count;
            else
            {
                tmpA = processRectangle(tmpA,nextCoordinates);
                count++;
            }
        }
    }
}

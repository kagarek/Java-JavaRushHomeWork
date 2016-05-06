package com.javarush.test.level36.lesson08.task01;

import java.io.IOException;

/**
 * Created by Igor_Makarychev on 08.04.2016.
 */
public class Run
{
    public static void main(String[] args) throws IOException
    {
        String[] a1 = {"./resources/level36/lesson08/task01/file1.txt"};
        String[] a2 = {"./resources/level36/lesson08/task01/file2.txt"};
        String[] a3 = {"./resources/level36/lesson08/task01/file3.txt"};

        new Solution().main(a1);
        System.out.println();
        new Solution().main(a2);
        System.out.println();
        new Solution().main(a3);
    }
}

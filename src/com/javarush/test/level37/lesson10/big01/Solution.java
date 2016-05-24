package com.javarush.test.level37.lesson10.big01;

import java.io.*;
import java.util.Arrays;

/**
 * Created by igormakarychev on 5/24/16.
 */
public class Solution {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AmigoSet<Integer> amigoSet = new AmigoSet<>(Arrays.asList(1,3,4,7));
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("/Users/igormakarychev/Documents/Java projects/Java-JavaRushHomeWork/src/com/javarush/test/level37/lesson10/big01/tmp.txt"));
        oos.writeObject(amigoSet);

        oos.close();

        ObjectInputStream ois;
        ois = new ObjectInputStream(
                new FileInputStream("/Users/igormakarychev/Documents/Java projects/Java-JavaRushHomeWork/src/com/javarush/test/level37/lesson10/big01/tmp.txt"));

        AmigoSet<Integer> amigoSet_deserial = (AmigoSet<Integer>) ois.readObject();

        ois.close();

        if (amigoSet.equals(amigoSet_deserial)) System.out.println("equal");
        else System.out.println("not equal");
    }
}

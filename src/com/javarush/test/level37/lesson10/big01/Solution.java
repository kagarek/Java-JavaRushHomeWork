package com.javarush.test.level37.lesson10.big01;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Igor_Makarychev on 5/27/2016.
 */
public class Solution {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AmigoSet<Integer> amigoSet = new AmigoSet<Integer>(Arrays.asList(1, 3, 4, 7));
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./src/com/javarush/test/level37/lesson10/big01/tmp.txt"));
        oos.writeObject(amigoSet);

        oos.close();

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("./src/com/javarush/test/level37/lesson10/big01/tmp.txt"));

        AmigoSet<Integer> amigoSet_deserial = (AmigoSet<Integer>) ois.readObject();

        ois.close();
        if (amigoSet.equals(amigoSet_deserial))
        {
            System.out.println(amigoSet.size());
            System.out.println(amigoSet_deserial.size());
            System.out.println("equals");
        }
    }
}

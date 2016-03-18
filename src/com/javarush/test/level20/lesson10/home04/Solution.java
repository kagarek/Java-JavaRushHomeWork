package com.javarush.test.level20.lesson10.home04;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Solution implements Serializable
{
    public static final long serialVersionUID = 170501993;

    public static void main(String args[]) throws Exception {
        String fileName = "C:\\Users\\ima\\Dropbox\\Projects\\Java\\JavaRush Projects\\JavaRushHomeWork\\src\\com\\javarush\\test\\level20\\lesson10\\home04\\1test.txt";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));

        Solution solution = new Solution();
        outputStream.writeObject(solution);
        outputStream.close();

        //loading
        ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(fileName));
        Solution loadedObject = (Solution) objectStream.readObject();
        objectStream.close();

        //Attention!!
        System.out.println(loadedObject.size());

    }

    private Map<String, String> m = new HashMap<String, String>();

    public Map<String, String> getMap() {
        return m;
    }

    public Solution() {
        m.put("Mickey", "Mouse");
        m.put("Mickey", "Mantle");
    }

    public int size() {
        return m.size();
    }
}
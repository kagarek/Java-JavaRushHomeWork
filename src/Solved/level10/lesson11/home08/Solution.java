package solved.level10.lesson11.home08;

import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк.
Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        //Напишите тут ваш код

        ArrayList<String> s1 = new ArrayList();
        ArrayList<String> s2 = new ArrayList();
        ArrayList<String> s3 = new ArrayList();

        s1.add("01");
        s1.add("02");
        s1.add("03");

        s2.add("04");
        s2.add("05");
        s2.add("06");

        s3.add("07");
        s3.add("08");
        s3.add("09");

        //System.out.println(s1);
        //System.out.println(s2);
        //System.out.println(s3);

        ArrayList<String>[] arrayOfStrings = new ArrayList[3];

        arrayOfStrings[0] = s1;
        arrayOfStrings[1] = s2;
        arrayOfStrings[2] = s3;

        return arrayOfStrings;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}
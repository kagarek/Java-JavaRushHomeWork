package solved.level08.lesson11.home03;

import java.util.HashMap;
import java.util.Map;

/* Люди с одинаковыми именами и/или фамилиями
1. Создать словарь Map (<String, String>) и добавить туда 10 человек в виде «Фамилия»-«Имя».
2. Пусть среди этих 10 человек есть люди с одинаковыми именами.
3. Пусть среди этих 10 человек есть люди с одинаковыми фамилиями.
4. Вывести содержимое Map на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList()
    {
        //Напишите тут ваш код
        Map<String,String> map = new HashMap<String, String>();

        map.put("Фамилия1","Имя5");
        map.put("Фамилия2","Имя4");
        map.put("Фамилия3","Имя3");
        map.put("Фамилия4","Имя2");
        map.put("Фамилия5","Имя1");

        map.put("Фамилия6","Имя8");
        map.put("Фамилия7","Имя7");
        map.put("Фамилия3","Имя6");
        map.put("Фамилия2","Имя5");
        map.put("Фамилия1","Имя4");

        return map;
    }

    public static void printPeopleList(Map<String, String> map)
    {
        for (Map.Entry<String, String> s : map.entrySet())
        {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }

}

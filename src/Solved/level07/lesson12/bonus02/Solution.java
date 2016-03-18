package solved.level07.lesson12.bonus02;

import java.util.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        HashMap<String, Date> map = createMap();

        removeAllSummerPeople(map);
    }

    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();

        map.put("Сталлоне", new Date("JUNE 1 1980"));
        map.put("Коля", new Date("AUGUST 27 1971"));
        map.put("Фрол", new Date("JANUARY 15 1992"));
        map.put("Света", new Date("FEBRUARY 3 1976"));
        map.put("Вася", new Date("SEPTEMBER 30 2000"));
        map.put("Ника", new Date("JUNE 8 1983"));
        map.put("Джони", new Date("AUGUST 7 1994"));
        map.put("Федор", new Date("JUNE 26 1989"));
        map.put("Катя", new Date("AUGUST 13 1997"));
        map.put("Гоблин", new Date("JULY 29 2004"));

        return map;

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //Напишите тут ваш код

        Iterator<Map.Entry<String, Date>> itr = map.entrySet().iterator();
        while (itr.hasNext())
        {
            int month = itr.next().getValue().getMonth() + 1;
            if (month >= 6 && month <= 8) itr.remove();
        }
    }
}

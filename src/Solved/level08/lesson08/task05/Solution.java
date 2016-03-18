package solved.level08.lesson08.task05;

import java.util.*;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{

    public static void main(String[] args)
    {
        HashMap<String, String> map = createMap();

        removeTheFirstNameDuplicates(map);
    }

    public static HashMap<String, String> createMap()
    {
        //Напишите тут ваш код
        HashMap<String, String> map = new HashMap<String, String>();

        for (int i = 0; i<8; i++)
        {
            map.put("Фамилия"+i, "Имя"+i);
        }

        map.put("Фамилия"+100, "Имя3");
        map.put("Фамилия"+101, "Имя4");


        return map;

    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //Напишите тут ваш код
        ArrayList<String> tmpArray = new ArrayList<String>(map.values());


        for (String s1 : tmpArray)
        {
            int i = 0;
            for (String s2 : tmpArray)
            {
                if (s1.equals(s2))
                    i++;
                if (i > 1)
                {
                    removeItemFromMapByValue(map, s1);
                    i = 0;
                    break;
                }
            }
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}

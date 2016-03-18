package solved.level09.lesson11.home09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        //Напишите тут ваш код
        Map<String,Cat> cats = new HashMap<String, Cat>();

        cats.put("Имя 1",new Cat("Кот 1"));
        cats.put("Имя 2",new Cat("Кот 2"));
        cats.put("Имя 3",new Cat("Кот 3"));
        cats.put("Имя 4",new Cat("Кот 4"));
        cats.put("Имя 5",new Cat("Кот 5"));
        cats.put("Имя 6",new Cat("Кот 6"));
        cats.put("Имя 7",new Cat("Кот 7"));
        cats.put("Имя 8",new Cat("Кот 8"));
        cats.put("Имя 9",new Cat("Кот 9"));
        cats.put("Имя 10",new Cat("Кот 10"));

        return cats;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        //Напишите тут ваш код

        Set<Cat> catSet = new HashSet<Cat>();

        for (Map.Entry<String,Cat> x : map.entrySet() )
            catSet.add(x.getValue());

        return catSet;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}

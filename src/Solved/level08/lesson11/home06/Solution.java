package solved.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //Написать тут ваш код

        ArrayList<Human> grandChildren = new ArrayList<Human>();
        ArrayList<Human> children1 = new ArrayList<Human>();
        ArrayList<Human> children2 = new ArrayList<Human>();

        Human grandChild1 = new Human("Р1",10,true,new ArrayList<Human>());
        Human grandChild2 = new Human("Р2",11,true,new ArrayList<Human>());
        Human grandChild3 = new Human("Р3",12,false,new ArrayList<Human>());

        grandChildren.add(grandChild1);
        grandChildren.add(grandChild2);
        grandChildren.add(grandChild3);

        Human father = new Human("О",40, true, grandChildren);
        Human mother = new Human("М",35, false, grandChildren);

        children1.add(father);
        children2.add(mother);

        Human grandPa1 = new Human("Д1",80,true,children1);
        Human grandMo1 = new Human("Б1",80,false,children1);

        Human grandPa2 = new Human("Д2",90,true,children2);
        Human grandMo2 = new Human("Б2",90,false,children2);

        System.out.println(grandPa1.toString());
        System.out.println(grandMo1.toString());
        System.out.println(grandPa2.toString());
        System.out.println(grandMo2.toString());
        System.out.println(father.toString());
        System.out.println(mother.toString());
        System.out.println(grandChild1.toString());
        System.out.println(grandChild2.toString());
        System.out.println(grandChild3.toString());



    }

    public static class Human
    {
        //Написать тут ваш код

        String name;
        int age;
        boolean sex;
        ArrayList<Human> children = new ArrayList<Human>();

        public Human (String name, int age, boolean sex, ArrayList<Human> children)
        {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = (this.children.isEmpty()) ? 0 : this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}

package solved.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human).
Создай объекты и заполни их так, чтобы получилось:
Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.

Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        //Написать тут ваш код
        Human grandPa1 = new Human("БОM1",true,100,null,null);
        Human grandPa2 = new Human("БОM2",true,100,null,null);

        Human grandMo1 = new Human("БОЖ1",false,100,null,null);
        Human grandMo2 = new Human("БОЖ2",false,100,null,null);

        Human father = new Human("БОM3",true,50,grandPa1,grandMo1);
        Human mother = new Human("БОЖ3",false,50,grandPa2,grandMo2);

        Human son1 = new Human("БОM4",true,10,father,mother);
        Human son2 = new Human("БОM5",true,10,father,mother);
        Human daughter1 = new Human("БОM6",false,10,father,mother);

        System.out.println(grandPa1.toString());
        System.out.println(grandPa2.toString());
        System.out.println(grandMo1.toString());
        System.out.println(grandMo2.toString());
        System.out.println(father.toString());
        System.out.println(mother.toString());
        System.out.println(son1.toString());
        System.out.println(son2.toString());
        System.out.println(daughter1.toString());
    }

    public static class Human
    {
        //Написать тут ваш код
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        Human(String name, boolean sex, int age, Human father, Human mother)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}

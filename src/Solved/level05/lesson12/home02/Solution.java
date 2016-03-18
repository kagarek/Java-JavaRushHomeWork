package solved.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        // Создай по два объекта каждого класса тут
        Man man1 = new Man("man name 1",20,"man address 1");
        Man man2 = new Man("man name 2",30,"man address 2");

        Woman woman1 = new Woman("woman name 1",21,"woman address 1");
        Woman woman2 = new Woman("woman name 2",22,"woman address 2");

        // Выведи их на экран тут

        System.out.println(man1.getName() + " " + man1.getAge() + " " + man1.getAddress());
        System.out.println(man2.getName() + " " + man2.getAge() + " " + man2.getAddress());
        System.out.println(woman1.getName() + " " + woman1.getAge() + " " + woman1.getAddress());
        System.out.println(woman2.getName() + " " + woman2.getAge() + " " + woman2.getAddress());

    }

    // Напиши тут свои классы

    public static class Man
    {
        String name, address;
        int age;


        public Man(String name, int age, String address)
        {
            this.name = name;
            this.age = age;
            this.address = address;
        }


        public String getName()
        {
            return name;
        }

        public String getAddress()
        {
            return address;
        }

        public int getAge()
        {
            return age;
        }
    }

    public static class Woman
    {
        String name, address;
        int age;

        public Woman(String name, int age, String address)
        {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public String getName()
        {
            return name;
        }

        public String getAddress()
        {
            return address;
        }

        public int getAge()
        {
            return age;
        }
    }
}

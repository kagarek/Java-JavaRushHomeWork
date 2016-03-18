package solved.level05.lesson12.home03;

/* Создай классы Dog, Cat, Mouse
Создай классы Dog, Cat, Mouse. Добавь по три поля в каждый класс, на твой выбор.
Создай объекты для героев мультика Том и Джерри. Так много, как только вспомнишь.
Пример:
Mouse jerryMouse = new Mouse(“Jerry”, 12 , 5), где 12 - высота в см, 5 - длина хвоста в см.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Mouse jerryMouse = new Mouse("Jerry", 12 , 5);

        //Напишите тут ваш код
        Dog bigDog = new Dog("Rex",20,"grey");
        Dog puppy = new Dog("good boy", 3, "grey");

        Cat tomCat = new Cat("Tom",3,true);
    }

    public static class Mouse
    {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail)
        {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }

    //Напишите тут ваши классы

    public static class Dog
    {
        String name;
        int weight;
        String color;

        public Dog(String name, int weight, String color)
        {
            this.name = name;
            this.weight = weight;
            this.color = color;
        }
    }

    public static class Cat
    {
        String name;
        int age;
        boolean lucky;

        public Cat(String name, int age, boolean lucky)
        {
            this.name = name;
            this.age = age;
            this.lucky = lucky;
        }
    }
}

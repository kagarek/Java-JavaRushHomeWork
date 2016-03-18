package solved.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        String firstName;
        String lastName;
        int age;
        int weight;
        int height;
        boolean sex;

        //1
        Human(String firstName,String lastName, int age, int weight, int height, boolean sex)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }

        //2
        Human(String firstName, int age)
        {
            this.firstName = firstName;
            this.age = age;
        }

        //3
        Human(String firstName, String lastName, boolean sex)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.sex = sex;
        }

        //4
        Human(String lastName, int weight, int height)
        {
            this.lastName = lastName;
            this.weight = weight;
            this.height = height;
        }

        //5
        Human(String lastName, int age, int weight, int height)
        {
            this.lastName = lastName;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        //6
        Human(String firstName, String lastName, int age, int weight, int height)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        //7
        Human(String firstName, String lastName, int age, boolean sex)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.sex = sex;
        }

        //8
        Human(String firstName, String lastName, int age)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        //9
        Human(String firstName, String lastName)
        {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        //10
        Human(String firstName, int weight, int height, boolean sex)
        {
            this.firstName = firstName;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }

    }
}

package solved.level06.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Нужно добавить в программу новую функциональность
Задача: У каждой кошки есть имя и кошка-мама. Создать класс, который бы описывал данную ситуацию.
Создать два объекта: кошку-дочь и кошку-маму. Вывести их на экран.

Новая задача: У каждой кошки есть имя, кошка-папа и кошка-мама. Изменить класс Cat так, чтобы он мог описать данную ситуацию.

Создать 6 объектов: маму, папу, сына, дочь, бабушку(мамина мама) и дедушку(папин папа).
Вывести их всех на экран в порядке: дедушка, бабушка, папа, мама, сын, дочь.

Пример ввода:
дедушка Вася
бабушка Мурка
папа Котофей
мама Василиса
сын Мурчик
дочь Пушинка

Пример вывода:
Cat name is дедушка Вася, no mother, no father
Cat name is бабушка Мурка, no mother, no father
Cat name is папа Котофей, no mother, father is дедушка Вася
Cat name is мама Василиса, mother is бабушка Мурка, no father
Cat name is сын Мурчик, mother is мама Василиса, father is папа Котофей
Cat name is дочь Пушинка, mother is мама Василиса, father is папа Котофей
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String grandpaName = reader.readLine();
        Cat catGrandPa = new Cat(grandpaName);

        String grandmoName = reader.readLine();
        Cat catGrandMo = new Cat(grandmoName);

        String fatherName = reader.readLine();
        Cat catFather = new Cat(fatherName,catGrandPa,null);

        String motherName = reader.readLine();
        Cat catMother = new Cat(motherName,null,catGrandMo);

        String sonName = reader.readLine();
        Cat catSon = new Cat(sonName, catFather, catMother);

        String daughterName = reader.readLine();
        Cat catDaughter = new Cat(daughterName, catFather, catMother);

        System.out.println(catGrandPa.toString());
        System.out.println(catGrandMo.toString());

        System.out.println(catFather.toString());
        System.out.println(catMother.toString());

        System.out.println(catSon.toString());
        System.out.println(catDaughter.toString());
    }

    public static class Cat
    {
        private String name;
        private Cat parentFather;
        private Cat parentMother;

        Cat(String name)
        {
            this.name = name;
        }

        Cat(String name, Cat parentFather, Cat parentMother)
        {
            this.name = name;
            this.parentFather = parentFather;
            this.parentMother = parentMother;
        }

        @Override
        public String toString()
        {
            String output_text = "Cat name is " + name;

            if (parentMother == null)
                output_text = output_text + ", no mother";
            else
                output_text = output_text + ", mother is " + parentMother.name;

            if (parentFather == null)
                output_text = output_text + ", no father";
            else
                output_text = output_text + ", father is " + parentFather.name;

            return output_text;
        }
    }

}

package solved.level14.lesson06.home01;

/* Куриная фабрика
Написать Фабрику(Factory) по производству кур(Hen)
1. Создать класс Hen
1.1. Сделать его абстрактным
1.2. Добавить в класс абстрактный метод  int getCountOfEggsPerMonth()
1.3. Добавить в класс метод String getDescription(), который возвращает строку "Я курица."

2. Создать класс RussianHen, который наследуется от Hen
3. Создать класс UkrainianHen, который наследуется от Hen
4. Создать класс MoldovanHen, который наследуется от Hen
5. Создать класс BelarusianHen, который наследуется от Hen

6. В каждом из четырех последних классов написать свою реализацию метода getCountOfEggsPerMonth.
Методы должны возвращать количество яиц в месяц от данного типа куриц.

7. В каждом из четырех последних классов написать свою реализацию метода getDescription.
Методы должны возвращать строку вида:
<getDescription() родительского класса>  + <" Моя страна - SSSSS. Я несу N яиц в месяц.">
где SSSSS - название страны
где N - количество яиц в месяц

8. В классе HenFactory реализовать меетод getHen, который возвращает соответствующую стране породу кур
9. Все созданные вами классы должны быть в отдельных файлах
*/

public class Solution
{
    public static void main(String[] args)
    {
        Hen hen = HenFactory.getHen(Country.BELARUS);
        hen.getCountOfEggsPerMonth();
        hen.getDescription();
    }

    static class HenFactory
    {

        static Hen getHen(String country)
        {
            Hen hen = null;
            //add your code here

            if (country.equals("Russia"))
            {
                hen = new RussianHen();
            } else if (country.equals("Ukraine"))
            {
                hen = new UkrainianHen();
            } else if (country.equals("Moldova"))
            {
                hen = new MoldovanHen();
            } else if (country.equals("Belarus"))
                hen = new BelarusianHen();

            return hen;
        }
    }
}
package solved.level09.lesson06.task01;

/* Исключение при работе с числами
Перехватить исключение (и вывести его на экран), указав его тип, возникающее при выполнении кода:
int a = 42 / 0;
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        try
        {

            int a = 42 / 0;
        }
            //Напишите тут ваш код
        catch (Exception e)
        {
            //String s = e.getMessage();
            System.out.println(e);
        }
    }
}

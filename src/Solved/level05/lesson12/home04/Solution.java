package solved.level05.lesson12.home04;

/* Вывести на экран сегодняшнюю дату
Вывести на экран текущую дату в аналогичном виде "21 02 2014".
*/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution
{
    public static void main(String[] args)
    {
        //Напишите тут ваш код
        Date date = new Date();

        DateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        String s = formatter.format(date);
        System.out.println(s);
    }
}

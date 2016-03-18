package solved.level09.lesson02.task05;

/* Метод должен возвращать результат – глубину его стек-трейса
Написать метод, который возвращает результат – глубину его стек трейса – количество методов в нем
(количество элементов в списке). Это же число метод должен выводить на экран.
*/

public class Solution
{
    public static int getStackTraceDeep()
    {
        //Напишите тут ваш код
        int i = 0;

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        i = stackTraceElements.length;

        System.out.println(i);

        return i;
    }
}

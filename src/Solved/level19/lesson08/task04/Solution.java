package solved.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream oldStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PrintStream newStream = new PrintStream(outputStream);

        System.setOut(newStream);

        testString.printSomething();

        String[] resultParse = outputStream.toString().split(" ");

        System.setOut(oldStream);

        if (resultParse[1].equals("+"))
        {
            System.out.println(resultParse[0] + " + " + resultParse[2]  + " = " + (Integer.parseInt(resultParse[0])+Integer.parseInt(resultParse[2])));
        }

        if (resultParse[1].equals("-"))
        {
            System.out.println(resultParse[0] + " - " + resultParse[2]  + " = " + (Integer.parseInt(resultParse[0])-Integer.parseInt(resultParse[2])));
        }

        if (resultParse[1].equals("*"))
        {
            System.out.println(resultParse[0] + " * " + resultParse[2]  + " = " + (Integer.parseInt(resultParse[0])*Integer.parseInt(resultParse[2])));
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 * 6 = ");
        }
    }
}


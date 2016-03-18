package com.javarush.test.level34.lesson02.task03;

/* Разложение на множители с помощью рекурсии
Разложить целое число n > 1 на простые множители.
Вывести в консоль через пробел все множители ав порядке возрстания.
Написать рекуррентный метод для вычисления простых множителей.
Не создавайте статические переменные и поля класса.
Пример:
132
Вывод на консоль:
2 2 3 11
*/
public class Solution {
    public void recursion(int n) {
        if (n <= 1) return;
        for (int i = 2; i <= n; i++)
        {
            if (n/i == 1 && n%i == 0)
            {
                System.out.println(i);
                return;
            }
            else
            {
                if (n%i==0)
                {
                    System.out.print(i+" ");
                    n = n/i;
                    recursion(n);
                    return;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new Solution().recursion(54); //2 3 3 3
        new Solution().recursion(1); //
        new Solution().recursion(321); // 3 107
        new Solution().recursion(167); // 167
    }
}

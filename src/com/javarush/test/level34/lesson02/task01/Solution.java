package com.javarush.test.level34.lesson02.task01;

/* Числа Фибоначчи с помощью рекурсии
Почитать про числа Фибоначчи.
Реализовать логику метода fibonacci, где n - это номер элемента в последовательности Фибоначчи.
Не создавайте статические переменные и поля класса.
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fibonacci(9));     //34
        System.out.println(solution.fibonacci(5));     //5
        System.out.println(solution.fibonacci(2));     //1
        System.out.println(solution.fibonacci(1));     //1
    }

    public int fibonacci(int n) {
        int result;

        if (n == 0) return 0;
        else if (n == 1) return 1;
        else result = fibonacci(--n) + fibonacci(--n);

        return result;
    }
}

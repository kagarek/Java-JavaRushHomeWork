package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Date;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {

    public static int[] getNumbers(int N) {
        ArrayList<Integer> resultTemp = new ArrayList<>();
        ArrayList<Integer> multiplyTemp = new ArrayList<>();
        int sumTemp;
        int tempI;
        int multiplyTempCalc;

        for (int i = 1; i < N; i++)
        {
            sumTemp = 0;
            multiplyTemp.clear();

            if (i < 10) resultTemp.add(i);
            else
            {
                tempI = i;

                while (tempI > 0)
                {
                    if (tempI < 10)
                    {
                        multiplyTemp.add(tempI);
                        tempI = 0;
                    }
                    else
                    {
                        multiplyTemp.add(tempI % 10);
                        tempI = tempI / 10;
                    }
                }

                for (int tmp = 0; tmp < multiplyTemp.size(); tmp++)
                {
                    multiplyTempCalc = 1;

                    for (int tmpPower = 0; tmpPower < multiplyTemp.size(); tmpPower++)
                        multiplyTempCalc = multiplyTempCalc * multiplyTemp.get(tmp);

                    sumTemp = sumTemp + multiplyTempCalc;
                }
            }

            if (sumTemp == i && i > 9) resultTemp.add(sumTemp);
        }

        int[] result = new int[resultTemp.size()];

        for (int j = 0; j < resultTemp.size(); j++)
            result[j] = resultTemp.get(j);

        return result;
    }

    public static void main(String[] args)
    {

        long startTime = System.currentTimeMillis();
        long freeMemoryStart = Runtime.getRuntime().freeMemory();

        //int[] k = getNumbers(100000000);
        int[] k = getNumbers(146511208);
        //int[] k = getNumbers(2147483647);

        for (int i = 0; i < k.length; i++)
        {
            System.out.println(k[i]);
        }
        long endTime = System.currentTimeMillis();
        long freeMemoryEnd = Runtime.getRuntime().freeMemory();

        System.out.println("Time used: " + (endTime-startTime)/1000 + " seconds");
        System.out.println("Memory used: " + (freeMemoryStart-freeMemoryEnd)/1024d + " kb");
    }
}
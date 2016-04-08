package com.javarush.test.level36.lesson08.task01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) {
        String fileName = args[0];
        TreeSet<String> characterTreeSet = new TreeSet<>();
        int k = 0;

        try (FileInputStream fileInputStream = new FileInputStream(new File(fileName)))
        {
            while ( fileInputStream.available() > 0){
                Character character = (char) fileInputStream.read();
                String symbol = character.toString().toLowerCase();
                Matcher m = Pattern.compile("[A-Za-z]").matcher(symbol);
                if (m.find())
                    characterTreeSet.add(symbol);
            }
        }
        catch (IOException e) {}

        for (String s: characterTreeSet)
        {
            if (k < characterTreeSet.size() && k != 5)
                System.out.print(s);
                k++;
        }
    }
}

package solved.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Гласные и согласные буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа должна вывести на экран две строки:
1. первая строка содержит только гласные буквы
2. вторая - только согласные буквы и знаки препинания из введённой строки.
Буквы соединять пробелом.

Пример ввода:
Мама мыла раму.
Пример вывода:
а а ы а а у
М м м л р м .
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Написать тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        ArrayList<Character> s_vowel = new ArrayList<Character>();
        ArrayList<Character> s_no_vowel = new ArrayList<Character>();

        for (int i = 0; i < s.length(); i++)
        {

            if (isVowel(s.charAt(i)))
                s_vowel.add(s.charAt(i));
            else
                if (!s.substring(i,i+1).equals(" "))
                    s_no_vowel.add(s.charAt(i));
        }


        //System.out.println(s_vowel);
        //System.out.println(s_no_vowel);

        for (int i = 0; i < s_vowel.size(); i++)
            if (i != s_vowel.size()-1)
                System.out.print(s_vowel.get(i) + " ");
            else
                System.out.println(s_vowel.get(i));

        for (int i = 0; i < s_no_vowel.size(); i++)
            if (i != s_no_vowel.size()-1)
                System.out.print(s_no_vowel.get(i) + " ");
            else
                System.out.println(s_no_vowel.get(i));
    }


    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    //метод проверяет, гласная ли буква
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   //ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}

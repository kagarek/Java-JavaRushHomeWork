package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endX) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/

/*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
 */

public class Solution {
    public static void main(String[] args) {

        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        List<Word> allWords = detectAllWords(crossword, "SAME", "agpe", "eeop", "jroel", "rj", "oprek", "home", "oore");

        for (Word x : allWords)
        {
            System.out.println(x.toString());
        }
    }

    public static class Direction{
        int x;
        int y;

        Direction(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static List<Word> checkDirections(int[][] crossword, String word, String startingCoordinates)
    {
        List<Word> words = new LinkedList<>();

        int startX = Integer.parseInt(startingCoordinates.substring(0,startingCoordinates.indexOf(",")));
        int startY = Integer.parseInt(startingCoordinates.substring(startingCoordinates.indexOf(",")+1,startingCoordinates.length()));

        ArrayList<Direction> dirs = new ArrayList<>();
        dirs.add(new Direction(1, 0)); //forward y
        dirs.add(new Direction(-1, 0)); //backward y
        dirs.add(new Direction(0, 1)); //forward x
        dirs.add(new Direction(0, -1)); //backward x
        dirs.add(new Direction(1, 1)); //forward y, forward x
        dirs.add(new Direction(-1, -1)); //backward y, backward x
        dirs.add(new Direction(1, -1)); //forward y, backward x
        dirs.add(new Direction(-1, 1)); //backward y, forward x

        for (Direction dir : dirs)
        {
            try
            {
                int endX = startX;
                int endY = startY;
                Word newWord = null;
                String tempWord = "";

                while (tempWord.length() <= word.length())
                {
                    tempWord = tempWord + (char)crossword[endY][endX];

                    if (word.equals(tempWord))
                    {
                        newWord = new Word(word);
                        newWord.setStartPoint(startX,startY);
                        newWord.setEndPoint(endX,endY);
                        words.add(newWord);
                    }

                    endX = endX + dir.x;
                    endY = endY + dir.y;
                }
            }
            catch (ArrayIndexOutOfBoundsException a)
            {
                //System.out.println("error");
            }
        }

        return words;
    }

    public static ArrayList<String> getPossibleCoordinatesOfFirstLetter(int[][] crossword, String word)
    {
        ArrayList<String> possibleStarts = new ArrayList<>();

        word = word.toLowerCase();
        char[] wordLetters = word.toCharArray();

        for (int y = 0; y < crossword.length; y++)
        {
            for (int x = 0; x < crossword[y].length; x++)
            {
                if ((int) wordLetters[0] == crossword[y][x] && !possibleStarts.contains(x + "," + y))
                {
                    possibleStarts.add(x + "," + y);
                }
            }
        }

        return possibleStarts;
    }


    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> wordsList = new LinkedList<>();
        int wordsCount = words.length;

        for (int word = 0; word < wordsCount; word++)
        {
            String text = words[word].toLowerCase();

            ArrayList<String> possibleStarts = getPossibleCoordinatesOfFirstLetter(crossword, text);

            for (int i = 0; i < possibleStarts.size(); i++)
            {
                wordsList.addAll(checkDirections(crossword, text, possibleStarts.get(i)));
            }
        }

        return wordsList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}

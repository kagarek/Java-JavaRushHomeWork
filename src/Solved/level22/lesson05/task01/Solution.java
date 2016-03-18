package solved.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/
public class Solution {

    public static String getPartOfString(String string) throws TooShortStringException
    {
        if (string==null || string.length() - string.replace(" ","").length() < 4)
            throw new TooShortStringException();
        else
        {
            int f_space = string.indexOf(" ")+1;
            int l_space = 0;
            int i = 1;
            char[] chars = string.toCharArray();

            for (int j = f_space; j < chars.length; j++)
            {
                if (String.valueOf(chars[j]).equals(" "))
                    i++;

                if (i == 5)
                {
                    l_space = j;
                    break;
                }
            }

            if (i == 4)
            {
                l_space = string.length();
            }

            return string.substring(f_space,l_space);
        }
    }

    public static class TooShortStringException extends Exception {
    }
}

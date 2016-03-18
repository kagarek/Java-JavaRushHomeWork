package solved.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {

    public static void main(String[] args) throws TooShortStringException
    {
        System.out.println(getPartOfString(null));
        System.out.println(getPartOfString("qqq"));
        System.out.println(getPartOfString("qqq\teee"));
        System.out.println(getPartOfString("qqq\teee\twww"));
        System.out.println(getPartOfString("qqq\teee\twww\trrr"));
        System.out.println(getPartOfString("qqq eee www rrr"));
    }

    public static String getPartOfString(String string) throws TooShortStringException{
        if (string == null)
            throw new TooShortStringException();
        else {
            int f_tab = string.indexOf("\t")+1;
            int s_tab = string.indexOf("\t",f_tab);

            if (f_tab != -1 && s_tab != -1) {
                return string.substring(f_tab, s_tab);
            }
            else
                throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }
}

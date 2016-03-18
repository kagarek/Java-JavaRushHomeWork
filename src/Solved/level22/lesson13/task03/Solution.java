package solved.level22.lesson13.task03;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.
Критерии валидности:
        1) если номер начинается с '+', то он содержит 12 цифр
        2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
        3) может содержать 0-2 знаков '-', которые не могут идти подряд
        4) может содержать 1 пару скобок '(' и ')'  , причем если она есть, то она расположена левее знаков '-'
        5) скобки внутри содержат четко 3 цифры
        6) номер не содержит букв
        7) номер заканчивается на цифру

Примеры:
+38 050 123 45 67 - true
+38 050 123-45-67 - true
+38(050)123 45 67 - true
    050 123-45 67 - true
   (050)1234-56-7 - true

+38)050(123 45 67 - false
+38(050)1-23-45-6-7 - false
    050 ххх 45 67 - false
    050 123 45 6 - false
   (0)50 123 45 67 - false
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {

        if (telNumber==null || telNumber.length()<10)
        {
            //System.out.print("telNumber==null || telNumber.length()<10: ");
            return false;
        }

        if (telNumber.matches("\\w"))
        {
            //System.out.print("telNumber.matches(\"\\\\w\"): ");
            return false;
        }

        if (!telNumber.matches(".*\\d$"))
        {
            //System.out.print("!telNumber.matches(\".*\\\\d$\"): ");
            return false;
        }

        if (!telNumber.matches("^[\\d,\\(,\\+].*"))
        {
            //System.out.print("!telNumber.matches(\"^[\\\\d,\\\\(,\\\\+].*\"): ");
            return false;
        }

        if (telNumber.matches("^[\\d,\\(].*"))
        {
            if (telNumber.replaceAll("\\D","").length()!=10)
            {
                //System.out.print("telNumber.matches(\"^[\\\\d,\\\\(].*\") - digits != 10: ");
                return false;
            }
        }

        if (telNumber.matches("^[\\+].*"))
        {
            if (telNumber.replaceAll("\\D","").length()!=12)
            {
                //System.out.print("telNumber.matches(\"^[\\\\d,\\\\(].*\") - digits != 12: ");
                return false;
            }
        }

        if (telNumber.matches(".*[-]{2}.*") || telNumber.matches(".*-.*-.*-.*"))
        {
            //System.out.print("telNumber.matches(\".*[--].*\") || telNumber.matches(\".*-.*-.*-.*\"): ");
            return false;
        }

        String regEx = "^(\\+\\d*)?(?:(\\(?)\\d{3}\\)([\\d -]){7,9}$|([\\d -]){10,12})$";
        Pattern result = Pattern.compile(regEx);
        Matcher m = result.matcher(telNumber);
        return m.matches();
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/level22_lesson13_task03.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null)
        {
            if (line.equals("null"))
                System.out.println(line + " : " + checkTelNumber(null));
            else
                System.out.println(line + " : " + checkTelNumber(line));
        }
     }
}
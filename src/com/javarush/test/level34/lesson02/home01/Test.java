package com.javarush.test.level34.lesson02.home01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Igor_Makarychev on 05.02.2016.
 */
public class Test
{
    public static void main(String[] args)
    {
        String expression = "sin(2*(-5+1.5*4)+28)";
        String allDigits = "[\\-]{0,1}\\d*\\.?\\d*";

        String findMultiplying = "\\d*\\.?\\d*[^\\)]\\*[^\\(]\\d*\\.?\\d*";
        Matcher m = Pattern.compile(findMultiplying).matcher(expression);
        while (m.find())
        {
            String subExpression = m.group();
            if (subExpression.length()>0)
                System.out.println(subExpression + " = " + evaluateSimpleExpression(subExpression,"*",0));
        }
    }

    public static String evaluateSimpleExpression(String expression, String operation, int countOperation) {
        Double result = 0.00d;
        expression = expression.replace("(","");
        expression = expression.replace(")","");
        String[] expressionParts = expression.split("\\"+operation);

        if (expressionParts[0].substring(0,1).equals("-")) {
            countOperation++;
        }

        if (operation.equals("-")) {
            result = Double.parseDouble(expressionParts[0]) - Double.parseDouble(expressionParts[1]);
            countOperation++;
        }

        if (operation.equals("*")) {
            result = Double.parseDouble(expressionParts[0]) * Double.parseDouble(expressionParts[1]);
            countOperation++;
        }

        if (operation.equals("+")){
            result = Double.parseDouble(expressionParts[0]) + Double.parseDouble(expressionParts[1]);
            countOperation++;
        }

        return result+" "+countOperation;
    }

}

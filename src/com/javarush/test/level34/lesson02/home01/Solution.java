package com.javarush.test.level34.lesson02.home01;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Рекурсия для мат.выражения
На вход подается строка - математическое выражение.
Выражение включает целые и дробные числа, скобки (), пробелы, знак отрицания -, возведение в степень ^, sin(x), cos(x), tan(x)
Для sin(x), cos(x), tan(x) выражение внутри скобок считать градусами, например, cos(3 + 19*3)=0.5
Степень задается так: a^(1+3) и так a^4, что эквивалентно a*a*a*a.
С помощью рекурсии вычислить выражение и количество математических операций. Вывести через пробел результат в консоль.
Результат выводить с точностью до двух знаков, для 0.33333 вывести 0.33, использовать стандартный принцип округления.
Не создавайте статические переменные и поля класса.
Не пишите косвенную рекурсию.
Пример, состоящий из операций sin * - + * +:
sin( 2 * (-5 +1.5*4) + 28) = sin(2*(-5+6)+28) = sin(2*1+28) = sin(2+28) = sin(30) = 0.5
Результат:
0.5 6
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = "-5.64-sin(2*(cos(81+5+4-90)+1.5*4)+tan(360/2))*(-2)^(1+5)+1"; //expected output -20.00 18
        System.out.println(expression);
        solution.recursion(expression, 0); //expected output 16.36 16
        System.out.println();

        expression = "sin( 2 * (-5 +1.5*4) + 28)";
        System.out.println(expression);
        solution.recursion(expression, 0); //expected output 0.5 6

        expression = "-2-3-4*-5";   // -2-3+20 = -2+17 = 15
        System.out.println(expression);
        solution.recursion(expression, 0); // 15 4
    }

    public void recursion(final String expression, int countOperation) {
        //implement
        Double result;
        String[] subExpression;

        //Common regex
        String rLeftGap = "[\\(]{1}";
        String rRightGap = "[\\)]{1}";
        String rDoubleDigit = "[\\-]?[0-9]{1,}[\\.?0-9]{0,}";
        String rDoubleDigit_positive = "[0-9]{1,}[\\.?0-9]{0,}";
        String rPlus = "[\\+]{1}";
        String rMinus = "[\\-]{1}";
        String rMultiply = "[\\*]{1}";
        String rPower = "[\\^]{1}";
        String rDivide = "[/]{1}";
        String rSin = "sin";
        String rCos = "cos";
        String rTan = "tan";

        String cleanedExpression = expression.replace(" ","");
        cleanedExpression = cleanedExpression.replace("+-","-");
        cleanedExpression = cleanedExpression.replace("--","+");
        cleanedExpression = cleanedExpression.replaceAll("([\\(])([\\-]{1})([0-9]{1,}[\\.?0-9]{0,})([\\)])","$2$3");

        // find operation that should be calculated
        String[][] patterns = new String[14][2];

        patterns[0] = new String[]{"sin",   "("+rSin+rLeftGap+rDoubleDigit+rRightGap+")"};
        patterns[1] = new String[]{"cos",   "("+rCos+rLeftGap+rDoubleDigit+rRightGap+")"};
        patterns[2] = new String[]{"tan",   "("+rTan+rLeftGap+rDoubleDigit+rRightGap+")"};
        patterns[3] = new String[]{"sin",   "("+rSin+rDoubleDigit+")"};
        patterns[4] = new String[]{"cos",   "("+rCos+rDoubleDigit+")"};
        patterns[5] = new String[]{"tan",   "("+rTan+rDoubleDigit+")"};
        patterns[6] = new String[]{"^",     rDoubleDigit+rPower+rDoubleDigit};
        patterns[7] = new String[]{"*",     rDoubleDigit+rMultiply+rDoubleDigit};
        patterns[8] = new String[]{"/",     rDoubleDigit+rDivide+rDoubleDigit};
        patterns[9] = new String[]{"+",     rLeftGap+rDoubleDigit+rPlus+rDoubleDigit+rRightGap};
        patterns[10] = new String[]{"-",    rLeftGap+rDoubleDigit+rMinus+rDoubleDigit+rRightGap};
        patterns[11] = new String[]{"+",    rDoubleDigit_positive+rPlus+rDoubleDigit};
        patterns[12] = new String[]{"-",    rDoubleDigit+rMinus+rDoubleDigit};
        patterns[13] = new String[]{"",     rLeftGap+rDoubleDigit+rRightGap};

        for (int i = 0; i < patterns.length; i++)
        {
            if ((subExpression = getSubexpressionByPattern(cleanedExpression,patterns[i][1])) != null) {
                String[] stringResult = evaluateSimpleExpression(subExpression[0], patterns[i][0], countOperation);
                int subExpressionStart = Integer.parseInt(subExpression[1]);
                int subExpressionLength = Integer.parseInt(subExpression[2]);
                result = Double.parseDouble(stringResult[0]);
                countOperation = Integer.parseInt(stringResult[1]);

                cleanedExpression =
                        cleanedExpression.substring(0,subExpressionStart)+
                        result.toString()+
                        cleanedExpression.substring(subExpressionStart+subExpressionLength);

                System.out.println("    "+countOperation + ": " + subExpression[0] + " = " + result);
                System.out.println("               RESULT: "+cleanedExpression);
                System.out.println();

                break;
            }
            //if (i>4) cleanedExpression = cleanedExpression.replace("(-","(0-");
        }

        try
        {
            // if full expression is calculated, then result is ready
            result = Double.parseDouble(cleanedExpression);
            DecimalFormat df = new DecimalFormat("#0.00") ;
            System.out.println(df.format(result) + " " + countOperation);
        }
        catch (Exception e)
        {
            // call recursion for updated expression
            recursion(cleanedExpression,countOperation);
        }
    }

    public String[] evaluateSimpleExpression(String expression, String operation, int countOperation) {
        String[] resultArray = new String[2];
        String[] expressionParts = new String[2];
        Double result = 0d;

        if (expression.startsWith("(") && expression.endsWith(")"))
        {
            try
            {
                result = Double.parseDouble(expression.replace("(", "").replace(")", ""));
            }
            catch (Exception e)
            {
                expression = expression.replace("(", "").replace(")", "");
            }
        }

        //if (expression.startsWith("-") || expression.startsWith("(-")) countOperation++;

        if (operation.equals("^")){
            expressionParts = expression.split("\\"+operation);
            result = Math.pow(Double.parseDouble(expressionParts[0]),Double.parseDouble(expressionParts[1]));
        }

        if (operation.equals("*")) {
            expressionParts = expression.split("\\"+operation);
            result = Double.parseDouble(expressionParts[0]) * Double.parseDouble(expressionParts[1]);
        }

        if (operation.equals("/")){
            expressionParts = expression.split(operation);
            result = Double.parseDouble(expressionParts[0])/Double.parseDouble(expressionParts[1]);
        }

        if (operation.equals("+")){
            expressionParts = expression.split("\\"+operation);
            result = Double.parseDouble(expressionParts[0]) + Double.parseDouble(expressionParts[1]);
            if (Double.parseDouble(expressionParts[0])<0) countOperation++;
        }

        if (operation.equals("-")) {
            expressionParts[0] = expression.substring(0,expression.lastIndexOf("-"));
            expressionParts[1] = expression.substring(expression.lastIndexOf("-")+1,expression.length());
            //expressionParts = expression.split("\\"+operation);
            result = Double.parseDouble(expressionParts[0]) - Double.parseDouble(expressionParts[1]);
            if (Double.parseDouble(expressionParts[0])<0) countOperation++;
        }

        if (operation.equals("sin")){
            result = Math.sin(Math.toRadians(Double.parseDouble(expression.replace("sin", "").replace("(", "").replace(")", ""))));
        }

        if (operation.equals("cos")){
            result = Math.cos(Math.toRadians(Double.parseDouble(expression.replace("cos", "").replace("(","").replace(")",""))));
        }

        if (operation.equals("tan")){
            result = Math.tan(Math.toRadians(Double.parseDouble(expression.replace("tan", "").replace("(","").replace(")",""))));
        }

        countOperation++;

        DecimalFormat df = new DecimalFormat("#0.##");
        resultArray[0] = df.format(result);
        resultArray[1] = String.valueOf(countOperation);

        return resultArray;
    }

    public String[] getSubexpressionByPattern(String expression, String pattern){
        String[] result = new String[3];
        Matcher m = Pattern.compile(pattern).matcher(expression);
        if (m.find())
        {
            result[0] = m.group();
            result[1] = m.start()+"";
            result[2] = result[0].length()+"";
            return result;
        }
        return null;
    }

    public Solution() { /*don't delete*/ }
}

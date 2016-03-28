package com.javarush.test.level35.lesson04.home01;

public class GenericStatic {
    public static <Number> Number someStaticMethod(Object genericObject) {
        Number result = (Number) genericObject;
        System.out.println(result);
        return result;
    }
}

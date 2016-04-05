package com.javarush.test.level36.lesson06.task01;


import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] classes = Collections.class.getDeclaredClasses();

        for (int i = 0; i < classes.length; i++) {
            Class<?> clazz = classes[i];
            int mod = clazz.getModifiers();
            Class<?>[] interfaces = clazz.getInterfaces();
            for (int j = 0; j < interfaces.length; j++) {
                Class<?> intrfc = interfaces[j];
                if (intrfc.equals(List.class) && Modifier.isPrivate(mod) && Modifier.isStatic(mod)){
                    return clazz;
                }
            }

        }

        return null;
    }
}

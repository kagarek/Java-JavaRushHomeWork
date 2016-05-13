package com.javarush.test.level36.lesson06.task01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
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
            if (Modifier.isPrivate(mod) && Modifier.isStatic(mod) && List.class.isAssignableFrom(clazz)) {
                try {
                    List list = (List) clazz.newInstance();
                    list.add(new Object());
                    list.get(0);
                }
                catch (IndexOutOfBoundsException e) {
                    return clazz;
                } catch (InstantiationException e) {
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return Collections.EMPTY_LIST.getClass();
    }
}

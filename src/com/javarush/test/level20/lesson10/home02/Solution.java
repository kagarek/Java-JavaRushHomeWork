package com.javarush.test.level20.lesson10.home02;

import java.io.*;

/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуйте объект в методе getOriginalObject предварительно определив, какого именно типа там объект.
Реализуйте интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;

    public A getOriginalObject(ObjectInputStream objectStream) {
        try
        {
            Object o = objectStream.readObject();

            if (o instanceof B)
            {
                B b = (B) o;
                return b;
            }
            else
            {
                if (o instanceof A)
                {

                    A a = (A) o;
                    return a;
                }
            }
        }
        catch (Exception e) {}

        return null;
    }

    public class A implements Serializable {
        private static final long serialVersionUID = 1L;

    }

    public class B extends A {
        private static final long serialVersionUID = 1L;
        public B() {
            System.out.println("inside B");
        }
    }
}

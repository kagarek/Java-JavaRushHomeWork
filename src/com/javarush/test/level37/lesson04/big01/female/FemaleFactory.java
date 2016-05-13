package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by igormakarychev on 5/12/16.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if (age <= KidGirl.MAX_AGE)
            return new KidGirl();
        else if (age <= TeenGirl.MAX_AGE)
            return new TeenGirl();
        else
            return new Woman();
    }
}

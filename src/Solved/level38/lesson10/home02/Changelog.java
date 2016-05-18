package Solved.level38.lesson10.home02;

import java.lang.annotation.*;

@Target(value= ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Changelog {
    //напиши свой код
    Revision[] value() default {};
}

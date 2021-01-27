package ru.JavaLevel3.lesson7;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface AfterSuite {

    String description() default "Запускается 1 раз после тестирования";
}

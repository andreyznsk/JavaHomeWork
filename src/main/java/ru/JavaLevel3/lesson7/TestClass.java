package ru.JavaLevel3.lesson7;

import ru.JavaLevel2.Lesson3.Person;

import javax.management.ObjectName;
import java.lang.reflect.Method;
import java.util.*;

public class TestClass implements Comparable<TestClass> {
    static Class classLink;

    private static void getClassLink(String className) throws Exception{
        classLink = Class.forName(className);

    }

   public static void runTest(String className) throws Exception {
        try {
            getClassLink(className);
        } catch (Exception e) {
            System.err.println("invalid class name\n" + e.getMessage());
           return;
        }
       runTest(classLink);
    }

    public static void runTest(Class classLink) throws Exception {
      int beforeCount = 0;
      int afterCount = 0;
        try {
            TestClass.classLink  = classLink;
            System.out.println(classLink);
        } catch (Exception e) {
            System.err.println("invalid class name\n" + e.getMessage());
            return;
        }
        Method[] methods = classLink.getDeclaredMethods();

        for (Method m : methods) {
            if(m.isAnnotationPresent(BeforeSuite.class)){
                beforeCount++;
            }
        }
        if(beforeCount > 1 ) throw new RuntimeException("Не один BeforeSuite");

        for (Method m : methods) {
            if(m.isAnnotationPresent(AfterSuite.class)){
                afterCount++;

            }
        }
        if(afterCount > 1 ) throw new RuntimeException("Не один AfetrSuit");

        List<Method> methodList = new ArrayList<>();

        for (Method m : methods) {
            if(m.isAnnotationPresent(Test.class)){
              methodList.add(m);

            }
        }
        methodList.sort(Comparator.
                comparingInt((Method m) -> m.getAnnotation(Test.class).priority()).reversed());

        for (int i = 0; i < methods.length; i++) {
            if(methods[i].isAnnotationPresent(BeforeSuite.class)) {
             methodList.add(0,methods[i]);
            }
            if(methods[i].isAnnotationPresent(AfterSuite.class)) {
                methodList.add(methods[i]);
            }
        }



        for (Method m : methodList) {
                 m.invoke(null);
            }
        }




    public static void main(String[] args) {
        try {
            runTest("ru.JavaLevel3.lesson7.MyObjClass");
           // runTest(MyObjClass.class);
        } catch (Exception e) {
            System.err.println("Some Error");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    @Override
    public int compareTo(TestClass o) {
        return 0;
    }
}

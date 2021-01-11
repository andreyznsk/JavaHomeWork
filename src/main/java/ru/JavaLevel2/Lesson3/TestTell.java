package ru.JavaLevel2.Lesson3;

import java.util.Map;
import java.util.TreeMap;

public class TestTell {

    public static void main(String[] args) {
       TelBook telBook = new TelBook();
        telBook.printAll();
       telBook.add("Andrey", 111);
       telBook.add("Andrey", 222);
        telBook.add("Sergey", 333);
        telBook.add("Alex", 444);
        telBook.add("John", 555);
        telBook.add("Sergey", 666);
        telBook.add("Alex", 777);
        telBook.add("Kataya", 888);
        telBook.add("Lena", 999);
        telBook.add("Mike", 123);
        telBook.add("Mike", 124);
        telBook.add("Piter", 125);
        telBook.add("Lena", 126);
        telBook.add("Lena", 127);
        telBook.printAll();

        System.out.println("Поиск по имени Mike: " +  telBook.get("Mike"));
        System.out.println("Поиск по имени Alex: " + telBook.get("Alex"));


        System.out.println("Поиск по имени Dima: " +  telBook.get("Dima"));


    }
}

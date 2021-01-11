package ru.JavaLevel2.Lesson3;

import java.util.*;

public class TelBook {

    Map<Person, Set<Integer>> telBook = new TreeMap<>();

    public TelBook(){

    }

    public void add(String name, int tel){
       Person person = new Person(name);
       if(!telBook.containsKey(person)) telBook.put(person,new HashSet<>());
       telBook.get(person).add(tel);
    }

    public void printAll(){
        if (telBook.isEmpty()) System.out.println("Справочник пуст");
        for (Map.Entry<Person, Set<Integer>> o : telBook.entrySet()) {

            System.out.println(o.getKey() + ": " + o.getValue());

        }
    }

    public Set<Integer> get(String name) {
        return telBook.get(new Person(name));

    }
}


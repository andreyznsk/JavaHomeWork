package ru.JavaLevel2.Lesson3;

import java.util.Objects;

public class Person implements Comparable<Person> {

    private final String name;
    //private final int age;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "name: " + name;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        return this.name.compareTo(anotherPerson.getName());
    }
}





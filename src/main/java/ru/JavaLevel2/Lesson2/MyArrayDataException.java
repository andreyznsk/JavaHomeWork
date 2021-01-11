package ru.JavaLevel2.Lesson2;

public class MyArrayDataException extends RuntimeException{
    public MyArrayDataException(int i, int j) {
        super("Элемент: [" + i + "],[" +j + "] " + "Не соответствует числу");
        }
}

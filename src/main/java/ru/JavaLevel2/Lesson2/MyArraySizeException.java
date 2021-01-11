package ru.JavaLevel2.Lesson2;

public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException(int size){
        super("У массива длинна: " + size + " не соответствует 4х4");
    }


}

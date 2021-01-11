package ru.JavaLevel2.Lesson2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayCount {

    private static final int SIZE = 4;//Жесткая длинна в соотвествии с заданием

   public static int arrCount(String[][] s) throws RuntimeException{ //Метод подсчтеа массива
       int summ = 0;
       if(s.length!=SIZE) throw new MyArraySizeException(s.length);//выброс исключения по длинне 1 уровня
    for (String[] strings : s) if (strings.length != SIZE) throw new MyArraySizeException(s.length);//выброс исключения по длинне второго уровня
    for (int i = 0; i < SIZE; i++) //Двойной цикл проверки числовых значений в массиве
        for (int j = 0; j < SIZE; j++)
            try {
                summ +=  Integer.parseInt(s[i][j]);
            } catch(NumberFormatException e) {
                throw new MyArrayDataException(i,j);//выброс ошибки с информацией по последнему элементу
            }

    return summ;
}

public static void printStringArray(String[][] s) throws MyArraySizeException {
    if(s.length!=SIZE) throw new MyArraySizeException(s.length);
    for (String[] strings : s) if (strings.length != SIZE) throw new MyArraySizeException(s.length);
    int arr[] = new int[SIZE];

    try {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                arr[j] = Integer.parseInt(s[i][j]);
            }
            System.out.println(Arrays.toString(arr));
        }
    } catch (NumberFormatException e) {
        System.err.println("Массив не числовой");
    }

    System.out.println();
}


    public static void main(String[] args) {
        String[][] smallStringArray = new String[2][2];//Массив меньшего размера
        try {
            printStringArray(smallStringArray);
            } catch (MyArraySizeException e) {
            System.err.println(e.getMessage());
        }

        String[][] string = new String[SIZE][SIZE];//Не числовой массив
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(string[i], "1");
        }
        string[3][1] = "a";
        //string[2][1] = "a";

        try {
            printStringArray(string);
            System.out.println("Сумма массива = " + arrCount(string));
        } catch (MyArraySizeException e){
            System.err.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Еще раз Считаем сумму массива без исключений:");

        string[3][1] = "1";
        try {
            printStringArray(string);
             System.out.println("Сумма массива = " + arrCount(string));
        } catch (MyArraySizeException e){
            System.err.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println(e.getMessage());
        }
   }

}

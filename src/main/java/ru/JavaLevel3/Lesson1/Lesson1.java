package ru.JavaLevel3.Lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson1 {

    public static void swap(Object[] arr,int a,int b){//Метод для задания 1
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static <T>ArrayList arrToArrayList(List<?> arr){//Метод к заданию 2
        ArrayList<T> list = new ArrayList<>();
        for (Object o : arr) {
            list.add((T) o);
        }
        return list;
    }


    public static void main(String[] args) {

        Integer arrInt[] = {1,2,3,4,5,6};
        String arrStr[] = {"a","b","c","d"};
        //Задание 1
        System.out.println(Arrays.asList(arrInt));
        System.out.println(Arrays.asList(arrStr));
        swap(arrInt,0,5);
        swap(arrStr, 2,3);
        System.out.println(Arrays.asList(arrInt));
        System.out.println(Arrays.asList(arrStr));

        //Задание 2
        Integer a[] = {1,2,3,4};
        ArrayList<Integer> newList= arrToArrayList(Arrays.asList(a));
        ArrayList<String> newStrlist = arrToArrayList(Arrays.asList(arrStr));
        System.out.println(newList);
        System.out.println(newStrlist);
        System.out.println(arrStr);
        System.out.println(arrInt);

    }
}

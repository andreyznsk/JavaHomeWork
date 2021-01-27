package ru.JavaLevel3.lesson6;

import ru.JavaLevel3.lesson7.Test;

import java.util.HashSet;
import java.util.Set;

public class ArrayApp {

    public static int[] arrayFour(int[] arr){
        int[] newArr;
        int index = -1;

        for (int i = 0; (i < arr.length) && (index == -1); i++) {
            if (arr[i] == 4) {
                index = i;
            }
        }
       if(index==-1) throw new RuntimeException();
        for (int i = 0; i < arr.length; i++) if(arr[i]==4) index=i;

        if (index == arr.length-1) return new int[0];

        newArr = new int[arr.length-index-1];
        System.arraycopy(arr,index+1,newArr,0,arr.length-index-1);
        return newArr;


    }

    public static boolean isOneFour(int[] arr){
        Set<Integer> unicListInt = new HashSet<>();//Сэт для уникальных чисел
        for (Integer s: arr) unicListInt.add(s);
        return  (unicListInt.size()==2 && unicListInt.contains(1) && unicListInt.contains(4));
    }

    @Test
    public static void main(String[] args) {
        int[] b = new int[]{1,1,2,2,2};
        System.out.println(isOneFour(b));
    }

}

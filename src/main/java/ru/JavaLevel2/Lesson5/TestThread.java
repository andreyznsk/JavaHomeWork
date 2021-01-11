package ru.JavaLevel2.Lesson5;


import java.util.Arrays;


public class TestThread {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;



     public static float[] method1(){

        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время работы первого метода: " + (System.currentTimeMillis() - a));

        return arr;
    }

    public static float[] method2() throws InterruptedException {
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        Runnable task1 = () -> {
           for (int i = 0; i < HALF; i++) {
               a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
           }
       };

        Runnable task2 = () -> {
            for (int i = 0; i < HALF; i++) {

                a2[i] = (float) (a2[i] * Math.sin(0.2f + (i+HALF) / 5) * Math.cos(0.2f + (i+HALF) / 5) * Math.cos(0.4f + (i+HALF) / 2));
            }
        };


        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        Thread th1 = new Thread(task1);
        Thread th2 = new Thread(task2);
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);


        System.out.println("Время работы второго метода: " +(System.currentTimeMillis() - a));

        return arr;
    }

    public static void main(String[] args) throws InterruptedException {
        float[] arrSeq, arrPara;

        arrSeq = method1();
        arrPara = method2();
        System.out.println(Arrays.equals(arrSeq,arrPara));
    }

}

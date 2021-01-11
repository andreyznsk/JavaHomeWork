package ru.geekbrains.HW2;

public class HW2 {
    public static double Calc(int a, int b, int c, int d){ //Пункт 3
        return a * (b + (c / d));
    }

    public static boolean True_false (int a, int b){ //Пункт 4
        int c = a+b;
        if (c >= 10 & c <= 20) return true;
        else return false;
    }

    public static void Check(int a){ //Пункт 5
        if (a>=0) System.out.println("Число положительное");
        else System.out.println("Число отрицательное");
        }

    public static boolean CheckZero(int a){ //Пункт 6
         return a<0;

        }

    public static void PrintHello (String name){ //Пункт 7
        System.out.println("Привет, " + name + "!");

    }
    public static void main(String[] args) {
        boolean bul = true;
        byte aByte = 120;
        short bShort = 1200;
        int cInt = 6553500;
        long dLong = 10000000000L;
        float eFloat = 1.0f;
        double fDouble = 1000.123456;

        char chr = 'i';

        String str = "Hello world";

        //вызов п.3
        System.out.println("Результат п.3(10 * (20 + (40 / 20)) = " + Calc(10,20,40,20));
        //вызов п.4
        System.out.println("Результат п.4 = " + True_false(10, 5));
        System.out.println("Результат п.4 = " + True_false(10, 15));
        //вызов п.5
        Check(-10);
        Check(10);
        //вызов п.6
        System.out.println("Результат п.6 = " + CheckZero(-10));
        System.out.println("Результат п.6 = " + CheckZero(+10));
        //вызов п. 7
        PrintHello("Андрей");





    }



}

package ru.JavaLevel3.lesson4;

public class ThreadABC {
    static final Object mon = new Object();
    static volatile char currentNumThread = 'A';
    final static int NUM = 5;

    public static void main(String[] args) {

        new Thread(()->{
            try {
                for (int i = 0; i < NUM; i++) {
                    synchronized (mon) {
                        while (currentNumThread!='A') mon.wait();
                        System.out.print("A");
                        currentNumThread = 'B';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ).start();
        new Thread(()->{
            try {
                for (int i = 0; i < NUM; i++) {
                    synchronized (mon) {
                        while (currentNumThread!='B') mon.wait();
                        System.out.print("B");
                        currentNumThread = 'C';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ).start();

        new Thread(()->{
            try {
                for (int i = 0; i < NUM; i++) {
                    synchronized (mon) {
                        while (currentNumThread!='C') mon.wait();
                        System.out.print("C");
                        System.out.println();
                        currentNumThread = 'A';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ).start();
    }
}

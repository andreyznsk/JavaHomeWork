package ru.JavaLevel3.lesson4;

public class ThreadABC {
    static final Object mon = new Object();
    static volatile int currentNumThread = 1;
    final static int NUM = 5;

    public static void main(String[] args) {

        new Thread(()->{
            try {
                for (int i = 0; i < NUM; i++) {
                    synchronized (mon) {
                        while (currentNumThread!=1) mon.wait();
                        System.out.print("A");
                        currentNumThread = 2;
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
                        while (currentNumThread!=2) mon.wait();
                        System.out.print("B");
                        currentNumThread = 3;
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
                        while (currentNumThread!=3) mon.wait();
                        System.out.print("C");
                        System.out.println();
                        currentNumThread = 1;
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

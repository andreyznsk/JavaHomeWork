package ru.JavaLevel3.lesson5;

import ru.JavaLevel2.Lesson7.ClaintServer.commands.PublicMessageCommandData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static Semaphore tunnel = new Semaphore(CARS_COUNT / 2);
    public static Semaphore consolePrint = new Semaphore(1);
    public static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT+1);
    public static Lock winLock = new ReentrantLock();

    public static void main(String[] args) throws Exception{
        printMessages("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        cb.await();

        printMessages("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cb.await();
        cb.await();
        printMessages("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

    }

    public static void printMessages(String msg) throws InterruptedException {
        consolePrint.acquire();
        System.out.println(msg);
        consolePrint.release();
    }

}


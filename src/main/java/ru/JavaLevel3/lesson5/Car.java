package ru.JavaLevel3.lesson5;

import java.util.concurrent.ArrayBlockingQueue;


public class Car implements Runnable {
    private static int CARS_COUNT ;

    static {
        CARS_COUNT = 0;
    }

    private final Race race;
    private int speed;
    private String name;
    ArrayBlockingQueue<Integer> qe;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            MainClass.printMessages(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            MainClass.printMessages(this.name + " готов");
            MainClass.cb_start.await();
            MainClass.cb_print_start.await();

            for (int i = 0; i < race.getStages().size(); i++) race.getStages().get(i).go(this);

            if(MainClass.winLock.tryLock())
                MainClass.printMessages(getName() + " - WIN");

            MainClass.cb_end.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


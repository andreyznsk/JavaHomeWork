package ru.JavaLevel3.lesson5;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            MainClass.printMessages(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            MainClass.printMessages(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

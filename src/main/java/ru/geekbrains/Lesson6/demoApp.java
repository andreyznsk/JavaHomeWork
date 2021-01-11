package ru.geekbrains.Lesson6;

public class demoApp {
    public static void main(String[] args) {
        dog dog1 = new dog(3, "Шарик");
        dog1.getInfo();
        dog1.run(100);
        dog1.run(600);
        dog1.swim(2);
        dog1.swim(20);
        dog1.jump(1);
        dog1.jump(0.2);
        System.out.println("Создали более сильную собаку на 700м");
        dog dog2 = new dog(3,"РЭКС");
        dog2.distRun(700);
        dog2.run(600);



        System.out.println("-------------------------------------------------------");
        cat cat1 = new cat(3, "Мурзик");
        cat1.getInfo();
        cat1.run(100);
        cat1.run(600);
        cat1.swim(20);
        cat1.jump(1);
        cat1.jump(5);

        System.out.println("\nСоздали плавающего кота");
        cat1.setFalagSwimig(true);
        cat1.swim(2);
        cat1.swim(10);

    }
}

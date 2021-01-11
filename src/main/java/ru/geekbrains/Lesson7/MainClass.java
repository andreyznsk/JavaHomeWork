package ru.geekbrains.Lesson7;

public class MainClass {

    public static void main(String[] args) {
        Cat cat[] = new Cat[10];
        Plate plate = new Plate(100);

        for (int i = 0; i < cat.length; i++) {
            cat[i] = new Cat(("Cat NO="+i),(1+i*10));
            cat[i].eat(plate);
        }

        System.out.println("После еды!___________________________");

        for (int i = 0; i < cat.length; i++) cat[i].printInfo();

        plate.info();
        System.out.println("Добавляем еды________________________");
        plate.addFood(50);
        plate.info();


        for (int i = 0; i < cat.length; i++) {
            cat[i].eat(plate);
            }

        for (int i = 0; i < cat.length; i++) {
            cat[i].printInfo();
        }

        Cat last = new Cat("Кот который все съел", 45);
        last.eat(plate);
        last.printInfo();
        plate.info();
    }

}


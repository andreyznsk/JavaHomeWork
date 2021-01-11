package ru.JavaLevel3.Lesson1;

public class Lesson1_2 {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();

        for (int i = 0; i < 10; i++) {
            appleBox.addFruit(new Apple());
        }

        System.out.println("Общий вес коробки с яблоками: " + appleBox.getWeight());

        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 10; i++) {
            orangeBox.addFruit(new Orange());
        }

        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());

        System.out.println("Коробки равны? - " + appleBox.compare(orangeBox));

        for (int i = 0; i < 5; i++) {
            appleBox.addFruit(new Apple());
        }
        System.out.println("Общий вес коробки с яблоками: " + appleBox.getWeight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());
        System.out.println("А теперь равны? - " + appleBox.compare(orangeBox));

        //orangeBox.intersperse(appleBox); - Ошибка компиляции!

        Box<Apple> appleBox2 = new Box<>();// Создадим вторую коробку с яблоками.

        for (int i = 0; i < 5; i++) {
            appleBox2.addFruit(new Apple());
        }

        System.out.println("Общий вес коробки с яблоками 2: " + appleBox2.getWeight());
        System.out.println("Пересыпаем фрукты из коробки 1 в коробку 2");
        appleBox2.intersperse(appleBox);
        System.out.println("Общий вес коробки с яблоками 1: " + appleBox.getWeight());
        System.out.println("Общий вес коробки с яблоками 2: " + appleBox2.getWeight());

    }
}

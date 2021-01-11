package ru.geekbrains.Lesson5;


import javax.crypto.spec.PSource;

public class DemoApp {
    public static void main(String[] args) {
    PersonalCard person1 = new PersonalCard();//Используем стандартный конструктор
    person1.printPersonCard();//Печатаем карточку Дефолт

        person1.setFullname("Иванов И.И.");
        person1.setPosition("Engineer");
        person1.setAge(38);
        person1.setEMail("Mailmail.ru"); //проевка на ошибку в поче
        person1.setEMail("Mail@mail.ru");// проверка на длинну телефона
        person1.setTel("123456");
        person1.setTel("12345678901");
        person1.setSalary(30000);
        person1.printPersonCard();
        System.out.println("Сколько леет иванову:" + person1.getAge());//отднльный Геттер на возраст
        System.out.println("№_Приказа о приеме:" + person1.getHireOrder());//отднльный Геттер на приказ
        person1.setPosition("Manager");
        person1.setSalary(40000);
        System.out.printf("Иванова перевели на новую должность: %s, з.п. стала: %d\n",person1.getPosition(), person1.getSalary());
// Массив из сотрудников.
        PersonalCard[] persArray = new PersonalCard[5]; // Вначале объявляем массив объектов
        persArray[0] = new PersonalCard("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "89231231211", 30000, 30); // потом для каждой ячейки массива задаем объект
        persArray[1] = new PersonalCard("Petr Petrov", "Sales manager", "Petrov@mailbox.com", "89231231212", 20000, 37); // потом для каждой ячейки массива задаем объект
        persArray[2] = new PersonalCard("Andreev Anrey", "Plant manager", "Andreev@mailbox.com", "89231241213", 50000, 42); // потом для каждой ячейки массива задаем объект
        persArray[3] = new PersonalCard("Petrova Ekaterina", "Buh", "petrova@mailbox.com", "89231231214", 40000, 30); // потом для каждой ячейки массива задаем объект
        persArray[4] = new PersonalCard("Sidorov Sidor", "Driver", "Sidorov@mailbox.com", "89231231215", 30000, 52); // потом для каждой ячейки массива задаем объект
        System.out.println();
        System.out.println("Выводим всех кому больше 40 лет");
        for (int i = 0; i < persArray.length; i++) {
            if(persArray[i].getAge()>40) persArray[i].printPersonCard();
        }


        System.out.println("Сколько всего объектов создано? - " + PersonalCard.getPersonNumber());



    }
}

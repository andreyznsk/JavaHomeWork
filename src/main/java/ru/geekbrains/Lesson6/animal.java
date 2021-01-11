package ru.geekbrains.Lesson6;

public abstract class animal {//Объявляем суперкласс абстрактным, животных не будем создавать

   protected static int id = 0 ;//Статическая переменная всех класов для подсчета объектов


    private String type;//Тип животного, для сокращения методов печати
    private int age;
    private String name;

    public animal(int age, String name){// Стандартный конструктор
        id++;//Каждый раз когда создается объект, эта переменная +1
        this.age = age;
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void getInfo(){//Метод печачи информации о животном
        System.out.println("Тип животного: " + type);
        System.out.println("Имя животного: " + name + "\nВозраст животного: " + age);
    }

    protected void run(double let){//безуслоное выполнение команды
        System.out.println(type + " " + name + " пробежал(а):" + let);
    }
    protected void swim(double let){
        System.out.println(type + " " + name + " проплыл(а):" + let);
    }

    protected void jump(double let){
        System.out.println(type + " " + name + " прыгнул(а):" + let);
    }

    public int getId() {//Метод вывода количества созданных объектов данного класса
       return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

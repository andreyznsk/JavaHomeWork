package ru.geekbrains.Lesson6;

public class dog extends animal {

    private int distRun;//Дополнительная переменная класса определяющая максимальную длинн бега

    public dog(int age, String name) {
        super(age, name);
        this.setType("Собака");
        this.distRun = 400;//Значение длинны бега по умолчанию
    }

    public void distRun(int distRun) {
        this.distRun = distRun;
    }
    //Переопредеяем методы для своего класса
    @Override
    public void run(double  let) {
        if (let >= 0 && let <= this.distRun) super.run(let);
        else System.out.println(getType() + " не может выполнить команду Бег на " + let +" м");

    }

    @Override
    public void swim(double  let) {
        if (let >= 0 && let <= 10) super.swim(let);
        else System.out.println(getType()  + " не может выполнить команду Плыть на " + let +" м");
    }

    @Override
    public void jump(double  let) {
        if (let >= 0 && let <= 0.5) super.jump(let);
        else System.out.println(getType()  + " не может выполнить команду Прыгать на " + let +" м");
    }


}

package ru.JavaLevel2.lesson1;

public class Human implements Participant {
    private String name;
    private int maxRun = 10;
    private int maxJump = 10;

    public Human(String name, int maxRun, int maxJump) {
        this.name = name;
        this.maxJump = maxJump;
        this.maxRun = maxRun;

    }
    @Override
    public boolean run(int let) {
        if (let < maxRun) {
            System.out.printf("Человек по имени %s пробежал по дрожке\n",name);
            return true;
        }
        else {
            System.out.println("Челоек не справился с препядствием: дорожка");
            return false;
        }
    }
    @Override
    public boolean jump(int let){
        if (let < maxJump) {
            System.out.printf("Человек по имени %s перепрыгнул стену\n",name);
            return true;
        }
        else {
            System.out.println("Члоек не справился с препядствием: стена");
            return false;
        }
    }
}
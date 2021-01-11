package ru.JavaLevel2.lesson1;

public class Cat implements Participant {
    private String name;
    private int maxRun;
    private int maxJump;

    public Cat(String name, int MaxRun, int MaxJump) {

        this.name = name;
        this.maxRun = MaxRun;
        this.maxJump = MaxJump;
    }

    @Override
    public boolean run(int let) {
        if (let < maxRun) {
            System.out.printf("Кот по имени %s пробежал по дрожке\n",name);
            return true;
        }
        else {
            System.out.println("Кто не справился с препядствием: дорожка");
            return false;
        }
    }
    @Override
    public boolean jump(int let){
        if (let < maxJump) {
            System.out.printf("Кот по имени %s перепрыгнул стену\n",name);
            return true;
        }
        else {
            System.out.println("Кто не справился с препядствием: стена");
            return false;
        }
    }
}

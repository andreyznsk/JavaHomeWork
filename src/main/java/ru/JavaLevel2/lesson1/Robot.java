package ru.JavaLevel2.lesson1;

public class Robot implements Participant {
    private String name;
    private int maxRun = 100;
    private int maxJump = 1;

    public Robot(String name, int MaxRun, int MaxJump) {
        this.name = name;
        this.maxJump = MaxJump;
        this.maxRun = MaxRun;
    }


    @Override
    public boolean run(int let) {
        if (let < maxRun) {
            System.out.printf("Робот по имени %s пробежал по дрожке\n",name);
            return true;
        }
        else {
            System.out.println("Робот не справился с препядчтвием: дорожка");
            return false;
        }
    }
    @Override
    public boolean jump(int let){
        if (let < maxJump) {
            System.out.printf("Робот по имени %s перепрыгнул стену\n",name);
            return true;
        }
        else {
            System.out.println("Робот не справился с препядчтвием: стена");
            return false;
        }
    }
}

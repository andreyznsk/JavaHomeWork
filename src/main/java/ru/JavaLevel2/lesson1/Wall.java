package ru.JavaLevel2.lesson1;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean passMe(Participant o) {
        return o.jump(height);
    }

    @Override
    public void printMe() {
        System.out.println("Высота стены: " + height);
    }
}

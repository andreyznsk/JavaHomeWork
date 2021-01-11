package ru.JavaLevel2.lesson1;

public class Treadmill implements Obstacle{
    private int dist;

    public Treadmill(int dist) {
        this.dist = dist;
    }

    @Override
    public boolean passMe(Participant o){
        return o.run(dist);
    }

    @Override
    public void printMe() {
        System.out.println("Длинна дорожки: " + dist);
    }
}

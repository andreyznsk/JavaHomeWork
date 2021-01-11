package ru.JavaLevel2.lesson1;



public class RunApp {
    public static void main(String[] args) {
        Participant[] models = new Participant[3];
        Obstacle[] obstacle = new Obstacle[6];
        models[0] = new Cat("Басик",11,35);
        models[1] = new Human("Bob", 15,5);
        models[2] = new Robot("Cyborg", 500, 100);
        obstacle[0] = new Wall(10);
        obstacle[1] = new Wall(20);
        obstacle[2] = new Wall(30);
        obstacle[3] = new Treadmill(10);
        obstacle[4] = new Treadmill(20);
        obstacle[5] = new Treadmill(30);

        for (Obstacle o:obstacle) {
          o.printMe();
        }
        for (Participant model : models) {
            for (Obstacle value : obstacle) {
                if (!value.passMe(model)) break;
            }


        }

        }



    }


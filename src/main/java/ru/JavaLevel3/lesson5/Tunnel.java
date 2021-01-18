package ru.JavaLevel3.lesson5;

public class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                MainClass.printMessages(c.getName() + " готовится к этапу(ждет): " + description);
                if(MainClass.tunnel.availablePermits() == 0) {
                    MainClass.printMessages("В тоннеле нет мест");
                    MainClass.printMessages(c.getName() + " Ждет!");
                }
                MainClass.tunnel.acquire();
                MainClass.printMessages(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                MainClass.tunnel.release();
                MainClass.printMessages(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

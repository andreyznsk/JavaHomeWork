package ru.JavaLevel2.Lesson6.Server;

import java.io.IOException;
import java.util.Scanner;

public class EchoServer {

    public static Scanner sc = new Scanner(System.in);

   // public static final int SERVER_PORT1 = 8189;

    public static void main(String[] args) throws IOException {

        ServerThreads thread1 = new ServerThreads(8189);
        ServerThreads thread2 = new ServerThreads(8190);
        ServerThreads thread3 = new ServerThreads(8191);

        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread3.setDaemon(true);

        thread1.start();
        thread2.start();
        thread3.start();

/*

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        System.out.println("Connection close");}
*/
        while (true){
            System.out.println("Input message(type exit for exit): ");
            String command = sc.next();
            if (command.equals("exit")) break;
            if(thread1.isAlive()) thread1.sendMessage(command);
            if(thread2.isAlive()) thread2.sendMessage(command);
            if(thread3.isAlive()) thread3.sendMessage(command);


        }


        sc.close();
        System.out.println("End");


    }
}

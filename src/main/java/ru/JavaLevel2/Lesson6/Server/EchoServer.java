package ru.JavaLevel2.Lesson6.Server;

import java.io.IOException;
import java.util.Scanner;

public class EchoServer {

    public static Scanner sc = new Scanner(System.in);

    public static final int SERVER_PORT = 8189;

    public static void main(String[] args) throws IOException {

        ServerThreads thread1 = new ServerThreads(SERVER_PORT);

        thread1.setDaemon(true);

        thread1.start();

        while (true){
            System.out.println("Input message(type exit for exit): ");
            String command = sc.next();
            if (command.equals("exit")) break;
            if(thread1.isAlive()) thread1.sendMessage(command);


        }


        sc.close();
        System.out.println("End");


    }
}

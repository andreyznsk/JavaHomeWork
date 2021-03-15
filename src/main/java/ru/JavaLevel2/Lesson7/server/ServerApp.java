package ru.JavaLevel2.Lesson7.server;

import java.io.IOException;
import java.sql.*;

public class ServerApp {

        private static final int DEFAULT_PORT = 8189;

        public static void main(String[] args) {
            int port = DEFAULT_PORT;
            if (args.length != 0) {
                port = Integer.parseInt(args[0]);
            }

            try {
                new MyServer().start(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


package ru.JavaLevel2.Lesson7.server;

import ru.JavaLevel2.Lesson7.ClaintServer.Command;
import ru.JavaLevel2.Lesson7.server.Handler.ClientHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MyServer {
    private static final Logger logger = Logger.getLogger(MyServer.class.getName());
    private final List<ClientHandler> clients = new ArrayList<>();
    private final AuthService authService;

    public MyServer() {
        this.authService = new DataBaseAuthService();
        LogManager manager = LogManager.getLogManager();
        try {
            manager.readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.log(Level.SEVERE,"Сервер был запущен");
          //  System.out.println("Сервер был запущен");
            runServerMessageThread();
            authService.start();
            //noinspection InfiniteLoopStatement
            while (true) {
                waitAndProcessNewClientConnection(serverSocket);
            }
        } catch (IOException e) {
            logger.log(Level.WARNING,"Failed to accept new connection", e);



        } finally {
            authService.stop();
        }
    }

    private void runServerMessageThread() {
        Thread serverMessageThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String serverMessage = scanner.next();
                try {
                    broadcastMessage("Сервер: " + serverMessage, null);
                } catch (IOException e) {
                    logger.log(Level.WARNING,"failed to process serverMessage",e);
                }
            }
        });
        serverMessageThread.setDaemon(true);
        serverMessageThread.start();
    }

    private void waitAndProcessNewClientConnection(ServerSocket serverSocket) throws IOException {
        logger.log(Level.SEVERE,"Ожидание нового подключения....");
       // System.out.println("Ожидание нового подключения....");

        Socket clientSocket = serverSocket.accept();
        logger.log(Level.SEVERE,"Клиент подключился");
       // System.out.println("Клиент подключился");// /auth login password
        processClientConnection(clientSocket);
    }

    private void processClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) throws IOException {

        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            if (sender == null) {
                client.sendMessage(message);
            } else {
                client.sendMessage(sender.getNickname(), message);
            }

        }
    }

    public synchronized void subscribe(ClientHandler handler) throws IOException {
        clients.add(handler);
        notifyClientsUsersListUpdated(clients);
    }

    public synchronized void unsubscribe(ClientHandler handler) throws IOException {
        clients.remove(handler);
        notifyClientsUsersListUpdated(clients);
    }

    private void notifyClientsUsersListUpdated(List<ClientHandler> clients) throws IOException {
        List<String> usernames = new ArrayList<>();
        for (ClientHandler client : clients) {
            usernames.add(client.getNickname());
        }

        for (ClientHandler client : clients) {
//            List<String> usernames = clients.stream() Какойто- странный способ, может удалить его?
//                    .map(ClientHandler::getNickname)
//                    .collect(Collectors.toList());

            client.sendCommand(Command.updateUsersListCommand(usernames));
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy(String nickname) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void sendPrivateMessage(ClientHandler sender, String recipient, String privateMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(recipient)) {
                client.sendMessage(sender.getNickname(), privateMessage);
            }
        }
    }
}
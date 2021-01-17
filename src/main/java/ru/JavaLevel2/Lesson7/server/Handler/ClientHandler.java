package ru.JavaLevel2.Lesson7.server.Handler;

import ru.JavaLevel2.Lesson7.ClaintServer.Command;
import ru.JavaLevel2.Lesson7.ClaintServer.CommandType;
import ru.JavaLevel2.Lesson7.ClaintServer.commands.AuthCommandData;
import ru.JavaLevel2.Lesson7.ClaintServer.commands.AuthRegData;
import ru.JavaLevel2.Lesson7.ClaintServer.commands.PrivateMessageCommandData;
import ru.JavaLevel2.Lesson7.ClaintServer.commands.PublicMessageCommandData;
import ru.JavaLevel2.Lesson7.server.MyServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.JavaLevel2.Lesson7.ClaintServer.Command.*;


public class ClientHandler {

    private final MyServer myServer;
    private final Socket clientSocket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String nickname;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.myServer = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        in  = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            try {
                authentication();
                readMessages();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to close connection!");
                }
            }
        });
    }

    private void authentication() throws IOException {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(nickname==null) {//если логин не получен закрыть соединение
                    try {
                        sendCommand(closeByTimer());//Посылаем клиенту команду о разрыве соединения по таймеру
                        System.out.println("Закрыаем соединение");
                        closeConnection();

                    } catch (IOException e) {
                        System.err.println("Не смогли прервать подключение");

                    }
                }
            }
        };

        Timer timer = new Timer(true);

        timer.schedule(timerTask, 120000);//Запуск отдельного потока, который проверят ести ли логин
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }

            if(command.getType() == CommandType.CREATE_NEW_USER) {
                AuthRegData data = (AuthRegData) command.getData();
                String login = data.getLogin();
                String password = data.getPassword();
                String nickname = data.getNickname();
                if (myServer.getAuthService().insertUser(login,password,nickname)==0) {
                    sendCommand(errorCommand("Такой ник уже есть!!"));
                    continue;
                } else sendCommand(confirmationCommand("Регистрация прошла успешно!"));
            }

            if(command.getType() == CommandType.UPDATE_USER) {
                AuthRegData data = (AuthRegData) command.getData();
                String login = data.getLogin();
                String password = data.getPassword();
                String nickname = data.getNickname();

                if (myServer.getAuthService().updateUser(login,password,nickname)==0) {
                    sendCommand(errorCommand("Логин или пароль некорркетны!"));
                    continue;
                } else sendCommand(confirmationCommand("Ник успешно изменен."));
            }


            if (command.getType() == CommandType.AUTH) {
                AuthCommandData data = (AuthCommandData) command.getData();
                String login = data.getLogin();
                String password = data.getPassword();
                String nickname = myServer.getAuthService().getNickByLoginPass(login, password);
                if (nickname == null) {
                    sendCommand(errorCommand("Некорректные логин или пароль!"));
                    continue;
                }

                if (myServer.isNickBusy(nickname)) {
                    sendCommand(errorCommand("Такой пользователь уже вошел в чат!"));
                    continue;
                }

                sendCommand(authOkCommand(nickname));
                setNickname(nickname);
                myServer.broadcastMessage(String.format("Пользователь '%s' зашел в чат!", nickname), null);
                myServer.subscribe(this);
                return;
            }
        }
    }

    public void sendCommand(Command command) throws IOException {
        out.writeObject(command);
    }

    private Command readCommand() throws IOException {
        Command command = null;
        try {
            command = (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to read Command class");
            e.printStackTrace();
        }

        return command;
    }

    private void readMessages() throws IOException {
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }

            switch (command.getType()) {
                case PRIVATE_MESSAGE: {
                    PrivateMessageCommandData data = (PrivateMessageCommandData) command.getData();
                    String receiver = data.getReceiver();
                    String message = data.getMessage();
                    myServer.sendPrivateMessage(this, receiver, message);
                    break;
                }
                case PUBLIC_MESSAGE: {
                    PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                    String message = data.getMessage();
                    myServer.broadcastMessage(message, this);
                    break;
                }
                case END:
                    return;
                default:
                    throw new IllegalArgumentException("Unknown command type: " + command.getType());

            }
        }
    }

    private void closeConnection() throws IOException {
        myServer.unsubscribe(this);
        clientSocket.close();
    }


    public void sendMessage(String message) throws IOException {
        sendCommand(Command.messageInfoCommand(message));
    }

    public void sendMessage(String sender, String message) throws IOException {
        sendCommand(clientMessageCommand(sender, message));
    }

    public String getNickname() {
        return nickname;
    }

    private void setNickname(String nickname) {
        this.nickname = nickname;
    }
}


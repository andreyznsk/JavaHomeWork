package ru.JavaLevel2.Lesson7.server;
public interface AuthService {

    void start();
    void stop();

    String getNickByLoginPass(String login, String password);

    int insertUser(String login, String password, String nickname);

    int updateUser(String login, String password, String nickname);



}
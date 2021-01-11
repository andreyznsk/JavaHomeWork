package ru.JavaLevel2.Lesson7.server;

import java.io.File;
import java.sql.*;
public class DataBaseAuthService implements AuthService {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psSelect;
    private static PreparedStatement psInsert;
    private static PreparedStatement psUpdate;
    private static PreparedStatement psSelectAllNick;
    private static PreparedStatement psSelectNicByLoginPass;

    private static void prepareAllStatements() throws SQLException {
        psSelect = connection.prepareStatement("SELECT nickname FROM users WHERE login = ? AND password = ?;");
        psUpdate = connection.prepareStatement("UPDATE users SET nickname = ? WHERE login = ? AND password = ?");
        psInsert = connection.prepareStatement("INSERT INTO users (login,password,nickname) VALUES(?,?,?)");
        psSelectAllNick = connection.prepareStatement("SELECT nickname, login FROM users");
    }

    private static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() {
        try {
            stmt.close();
            System.out.println("Data base has been closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start() {
        System.out.println("Auth service is running");

        try {
            connect();
            prepareAllStatements();
            System.out.println("Connect to bd main is successful");
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            //disconnect();
        }




    }

    @Override
    public void stop() {
        System.out.println("Auth service has been stopped");
        disconnect();
    }

    @Override
    public String getNickByLoginPass(String login, String password) {//Изменил на метод идентификации из БД.

        try {//Блок провеки через подготовленный запрос
            psSelect.setString(1,login);
            psSelect.setString(2,password);
            ResultSet rs = psSelect.executeQuery();
            while (rs.next()){
                //System.out.println(rs.getString("nickname"));
                return rs.getString("nickname");
            }

        } catch (SQLException throwables) {
            System.err.println(throwables);
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertUser(String login, String password, String nickname) {
        try {//Блок провеки через подготовленный запрос
            psInsert.setString(1,nickname);
            psInsert.setString(2,login);
            psInsert.setString(3,password);
            //System.out.printf("Login: %s\npassword: %s\nNickname: %s\n",login,password,nickname);

            ResultSet rs = psSelectAllNick.executeQuery();
            String dataBaseNick;
            String dataBaseLogin;

            while (rs.next()){

                dataBaseNick = rs.getString("nickname");
                dataBaseLogin = rs.getString("login");
                System.out.println("nick: " + dataBaseNick);
                System.out.println("login: " + dataBaseLogin);
                if (dataBaseNick.equals(nickname)||dataBaseLogin.equals(login)) {
                    System.out.printf("Error, Nick or Login exists");
                   return 0;
                }
            }

            if(psInsert.executeUpdate()==1) {
                System.out.println("Insert is OK");
                return 1;
            }

                //ResultSet rs = psSelect.executeQuery();

        } catch (SQLException throwables) {
            System.err.println(throwables);
            throwables.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateUser(String login, String password, String nickname) {
        try {//Блок провеки через подготовленный запрос
            psUpdate.setString(1, nickname);
            psUpdate.setString(2, login);
            psUpdate.setString(3, password);
            //System.out.printf("Login: %s\npassword: %s\nNickname: %s\n",login,password,nickname);
            String oldNicname = getNickByLoginPass(login, password);

            File file = null;
            if (oldNicname != null)

                file = new File("src/main/java/ru/JavaLevel2/Lesson7/client/chatHisotory/chathistory_" + oldNicname + ".txt");
            System.out.println("oldNick = " + oldNicname);

            if (psUpdate.executeUpdate() == 1) {
                System.out.println("Update is OK");

                file.renameTo(new File("src/main/java/ru/JavaLevel2/Lesson7/client/chatHisotory/chathistory_" + nickname + ".txt"));
                file.delete();
                return 1;
            }

            //ResultSet rs = psSelect.executeQuery();

        } catch (SQLException throwables) {
            System.err.println(throwables);
            throwables.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
            return 0;



    }
}


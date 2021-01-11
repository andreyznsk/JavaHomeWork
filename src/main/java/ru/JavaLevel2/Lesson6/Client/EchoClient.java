package ru.JavaLevel2.Lesson6.Client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Scanner;


public class EchoClient extends Application {

    public static final String[] USERS_TEST_DATA = {"Oleg", "Alexey", "Peter"};
    private static int port;


    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EchoClient.class.getResource("/view.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));

        setStageForSecondScreen(primaryStage);

        primaryStage.show();

        Network network = new Network("localhost",port);
        if (!network.connect()) {
            showNetworkError("", "Failed to connect to server");
        }

        ViewController viewController = loader.getController();
        viewController.setNetwork(network);

        network.waitMessages(viewController);

        primaryStage.setOnCloseRequest(event -> {
//            network.sendMessage("/end");
//            network.close();
        });
    }

    public static void showNetworkError(String errorDetails, String errorTitle) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Network Error");
        alert.setHeaderText(errorTitle);
        alert.setContentText(errorDetails);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        System.out.print("Input port(8189,8190,8191): ");
        Scanner sc = new Scanner(System.in);
        port = sc.nextInt();

        launch(args);
    }

    private void setStageForSecondScreen(Stage primaryStage) {
        Screen secondScreen = getSecondScreen();
        Rectangle2D bounds = secondScreen.getBounds();
        primaryStage.setX(bounds.getMinX() + (bounds.getWidth() - 300) / 2);
        primaryStage.setY(bounds.getMinY() + (bounds.getHeight() - 200) / 2);
    }

    private Screen getSecondScreen() {
        for (Screen screen : Screen.getScreens()) {
            if (!screen.equals(Screen.getPrimary())) {
                return screen;
            }
        }
        return Screen.getPrimary();
    }
}
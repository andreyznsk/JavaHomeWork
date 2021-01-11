package ru.JavaLevel2.lesson4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    List<String> messages = new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources){
    //textArea.setText("No Text");
        textField.setText(null);

    }

    public void buttonExitAction() {
        Platform.exit();
    }

    public void buttonSendAction(ActionEvent actionEvent) {
        if (textField.getText()!=null) {
            {
                messages.add(textField.getText());
                String str = new String();

                for (int i = 0; i < messages.size(); i++) {
                    str += "(" + i + ") - " + messages.get(i) + '\n';
                }
                textArea.setText(str);
                textField.setText(null);
            }
        }
    }


    public void enter(KeyEvent keyEvent) {
        if (textField.getText()!=null) {
            {
                messages.add(textField.getText());
                String str = new String();

                for (int i = 0; i < messages.size(); i++) {
                    str += "(" + i + ") - " + messages.get(i) + '\n';
                }
                textArea.setText(str);
                textField.setText(null);
            }
        }
    }

    public void enterPress(ActionEvent actionEvent) {

        if (actionEvent.getEventType().toString() == "ACTION" && textField.getText()!=null) {
            {
                messages.add(textField.getText());
                String str = new String();

                for (int i = 0; i < messages.size(); i++) {
                    str += "(" + i + ") - " + messages.get(i) + '\n';
                }
                textArea.setText(str);
                textField.setText(null);
            }
        }
    }


}

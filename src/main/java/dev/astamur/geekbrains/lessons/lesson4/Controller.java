package dev.astamur.geekbrains.lessons.lesson4;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    HBox msgPanel;

    @FXML
    TextField msgField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized");
    }

    public void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
            alert.showAndWait();
        });
    }

    public void sendMsg() {
        showAlert(msgField.getText());
        msgField.clear();
        msgField.requestFocus();
    }
}

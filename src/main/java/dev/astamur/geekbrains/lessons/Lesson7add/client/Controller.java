package dev.astamur.geekbrains.lessons.Lesson7add.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextArea personalArea;

    @FXML
    TextField msgField, loginField;

    @FXML
    HBox msgPanel, authPanel;

    @FXML
    PasswordField passField;

    @FXML
    ListView<String> clientsList;

    private Network network;
    private String nickname;
    private boolean authed = false;

    public void setAuthenticated(boolean authenticated) {
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        msgPanel.setVisible(authenticated);
        msgPanel.setManaged(authenticated);
        clientsList.setVisible(authenticated);
        clientsList.setManaged(authenticated);
        personalArea.setVisible(authenticated);
        personalArea.setManaged(authenticated);
        if (!authenticated) {
            nickname = "";
        }
        authed = true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAuthenticated(false);
        clientsList.setOnMouseClicked(this::clientClickHandler);
        try {
            createNetwork();
        } catch (IOException e) {
            e.printStackTrace();
        }
        network.connect();
    }

    public void sendAuth() {
        network.sendAuth(loginField.getText(), passField.getText());
        loginField.clear();
        passField.clear();
    }

    public void sendMsg() {
        if (network.sendMsg(msgField.getText())) {
            msgField.clear();
            msgField.requestFocus();
        }
    }

    public void sendExit() {
        network.sendMsg("/end");
    }

    public void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
            alert.showAndWait();
        });
    }


    public void createNetwork() throws IOException {
        network = new Network();
        network.setCallOnException(args -> showAlert(args[0].toString()));
        network.setCallOnCloseConnection(args -> setAuthenticated(false));
        network.setCallOnAuthenticated(args -> {
            setAuthenticated(true);
            nickname = args[0].toString();
            //печатаем историю чере метод в текстовом поле textArea после аутентификации.
            network.printHistory(textArea);
            //.
        });
        network.setCallOnMsgReceived(args -> {
            String msg = args[0].toString();
            if (msg.startsWith("/w")) {
                personalArea.appendText(msg.substring(2));
            } else {
                textArea.appendText(msg + "\n" + " ");
            }
        });

    }

    private void clientClickHandler(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String nickname = clientsList.getSelectionModel().getSelectedItem();
            msgField.setText("/w " + nickname + " ");
            msgField.requestFocus();
            msgField.selectEnd();
        }
    }
}
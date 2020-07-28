package dev.astamur.geekbrains.lessons.Lesson7add.client;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Network implements Closeable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Callback callOnMsgReceived;
    private Callback callOnAuthenticated;
    private Callback callOnException;
    private Callback callOnCloseConnection;
    // объявление потока для записи
    public FileWriter chatHistory;
    //объявление потока для чтения.
    public FileReader readChatHistory;
    public int historyStringCounter = 0;
    //сканер для прохода по строкам.
    public Scanner chatScanner;
    //список сообщений в истории чата который мы заполняем в конструкторе Network.
    ArrayList<String> histToTextArea = new ArrayList<>();

    //В конструкторе открываем файл в который будем писать историю сообщений.
    public Network() throws IOException {
        this.chatHistory = new FileWriter("chat.txt", true);
        //открываем для чтения и подсчета строк истории.
        this.readChatHistory = new FileReader("chat.txt");
        this.chatScanner = new Scanner(readChatHistory);
        while (chatScanner.hasNext()) {
            historyStringCounter++;
            histToTextArea.add(chatScanner.nextLine());
        }
        //оставляем в ArrayList только последние 100 сообщений для вывода в textArea в Controller.
        if (historyStringCounter > 100) {
            histToTextArea = (ArrayList) (histToTextArea.subList((histToTextArea.size() - 100), histToTextArea.size()));
        }
    }


    public void setCallOnMsgReceived(Callback callOnMsgReceived) {
        this.callOnMsgReceived = callOnMsgReceived;
    }

    public void setCallOnAuthenticated(Callback callOnAuthenticated) {
        this.callOnAuthenticated = callOnAuthenticated;
    }

    public void setCallOnException(Callback callOnException) {
        this.callOnException = callOnException;
    }

    public void setCallOnCloseConnection(Callback callOnCloseConnection) {
        this.callOnCloseConnection = callOnCloseConnection;
    }

    public void sendAuth(String login, String password) {
        try {
            connect();
            out.writeUTF("/auth " + login + " " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        if (socket != null && !socket.isClosed()) {
            return;
        }

        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread clientListenerThread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/authok ")) {
                            callOnAuthenticated.callback(msg.split("\\s")[1]);
                            break;
                        } else {
                            callOnException.callback(msg);
                        }
                    }
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.equals("/end")) {
                            break;
                        }
                        callOnMsgReceived.callback(msg);
                    }
                } catch (IOException e) {
                    callOnException.callback("Соединение с сервером разорвано");
                } finally {
                    close();
                }
            });
            clientListenerThread.setDaemon(true);
            clientListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод обращающийся к ArrayList histToTextArea для последовательного получения всех строк и передачи в javafx.TextArea
    public void printHistory(javafx.scene.control.TextArea textArea) {
        for (String s : histToTextArea) {
            textArea.appendText(s + "\n");
        }
    }

    public boolean sendMsg(String msg) {
        if (out == null) {
            callOnException.callback("Соединение с сервером не установлено");
        }
        try {
            out.writeUTF(msg);
            //блок отвечающий за запись сообщений в файл.
            if (!msg.equals("/end")) {
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String toHistory = dateTime.format(myFormatObj) + " " + msg;
                chatHistory.write(toHistory);
                chatHistory.write("\n");
            }
            //.
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() {
        callOnCloseConnection.callback();
        close(in, out, socket, chatHistory, readChatHistory, chatScanner);
    }

    private void close(Closeable... objects) {
        for (Closeable o : objects) {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

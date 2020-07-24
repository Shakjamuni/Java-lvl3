package dev.astamur.geekbrains.lessons.Lesson7add.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    long counter = 0;
    boolean running = true;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
            //запускаем поток отсчета таймаута.
            new Thread(() -> {
                try {
                    while (running) {
                        Thread.sleep(1000);
                        counter++;
                        System.out.println(counter);
                        if (counter >= 120 && name.equals("")) {
                            sendMsg("Время авторизации на сервере вышло...");
                            in.close();
                            out.close();
                            socket.close();
                            running = false;
                        }
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (
                IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }

    }


    public void authentication() throws IOException, InterruptedException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        name = nick;
                        myServer.broadcastMsg(name + " зашел в чат");
                        myServer.subscribe(this);
                        running = false;
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }


    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals("/end")) {
                closeConnection();
                return;
            }

            //wisp mode
            if (strFromClient.startsWith("/w")) {
                // то - кому шепчем, msg - сообщение.
                String to = strFromClient.split("\\s")[1];
                String msg = strFromClient.split("\\s")[2];
                //проверяем шлем ли самому себе, и есть ли ник на сервере.
                if (!to.equals(name) && myServer.isNickBusy(to)) {
                    myServer.wispMsg(to, msg);
                } else if (to.equals(name)) {
                    myServer.wispMsg(name, "Вы пытаетесь отправить сообщение себе...");
                } else if (myServer.isNickBusy(to)) {
                    myServer.wispMsg(name, "Пользователя нет на сервере...");
                }
                //конец проверок с режимом /w
            } else {
                //шлем сообщение в общий чат
                myServer.broadcastMsg(name, strFromClient);
            }
        }

    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMsg(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

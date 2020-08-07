package dev.rumyancev.geekbrains.lessons.Lesson7add.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    static org.apache.log4j.Logger currentLogger = Logger.getLogger(MyServer.class);
    private final int PORT = 8189;
    private Map<String, ClientHandler> clients;
    private AuthService authService;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);


    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            currentLogger.info("authService started. Waiting for connection.");
            clients = new HashMap<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                executorService.execute(new ClientHandler(this, socket));
                currentLogger.info("Connection done, new ClientHandler instantiation.");

            }
        } catch (IOException | SQLException e) {
            System.out.println("Ошибка в работе сервера");
            currentLogger.error("Error of server runtime.");
        } finally {
            if (authService != null) {
                authService.stop();
                currentLogger.info("authService stopped.");
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        return clients.containsKey(nick);
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients.values()) {
            o.sendMsg(msg);
        }
    }


    public synchronized void broadcastMsg(String from, String msg) {
        broadcastMsg(formatMessage(from, msg));
    }

    public synchronized void wispMsg(String to, String msg) {
        clients.get(to).sendMsg("/w " + msg);
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o.getName());
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.put(o.getName(), o);
    }

    private String formatMessage(String from, String msg) {
        return from + ": " + msg;
    }
}

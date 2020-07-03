package Lessons.Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleChatClient {

    private final String serverAddress = "localhost";
    private final int serverPort = 7777;
    private Socket connection;
    public String message = null;
    private DataInputStream in;
    private DataOutputStream out;

    public void openConnection() throws IOException {
        connection = new Socket(serverAddress, serverPort);
        in = new DataInputStream(connection.getInputStream());
        out = new DataOutputStream(connection.getOutputStream());
        out.flush();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String messageFromServer = in.readUTF();
                        if (messageFromServer.equals("/end")) {
                            break;
                        }
                        System.out.println("Сообщение с сервера: " + messageFromServer);
                        System.out.print("-->");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void closeConnection() {
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
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public void sendMessage() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (connection.isConnected()) {
                    System.out.print("-->");
                    Scanner scanner = new Scanner(System.in);
                    message = scanner.nextLine();
                    try {
                        if (message.equals("/end")) {
                            closeConnection();
                        }
                        out.writeUTF(message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public static void main(String[] args) throws IOException {
        ConsoleChatClient client = new ConsoleChatClient();
        client.openConnection();
        client.sendMessage();
    }

}

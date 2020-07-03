package Lessons.Lesson6;


import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleChatServer {
    Socket socket = null;
    DataInputStream in;
    DataOutputStream out;
// запускаем поток в конструкторе объекта с инициализацией in, out, socket. Там же читаем in.
    ConsoleChatServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(7777)) {
                    System.out.println("Ожидание подключений...");
                    socket = serverSocket.accept();
                    System.out.println("Клиент подключился!");
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    out.flush();
                    while (true) {
                        String messageFromClient = in.readUTF();
                        if (messageFromClient.equals("stopChat")) {
                            break;
                        }
                        System.out.println("Сообщение от клиента: " + messageFromClient);
                        System.out.print("-->");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
//в main будет крутится этот метод для отправки сообщений клиенту.
     void sendMessage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("-->");
        String message = scanner.nextLine();
        if (message.equals("stopChat")) {
            System.exit(0);
        }
        this.out.writeUTF(message);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        ConsoleChatServer consoleChatServer = new ConsoleChatServer();
            while (true) {
                consoleChatServer.sendMessage();
            }
        }
    }


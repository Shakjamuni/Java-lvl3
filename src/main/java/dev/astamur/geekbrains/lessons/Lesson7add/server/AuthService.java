package dev.astamur.geekbrains.lessons.Lesson7add.server;

public interface AuthService {
    void start();

    String getNickByLoginPass(String login, String pass);

    void stop();
}

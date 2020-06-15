package dev.astamur.geekbrains.lessons.lesson1;

public class User {
    private int id;
    private String name;
    private String position;
    private int age;

    public User() {
    }

    public User(int id, String name, String position, int age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age con not be negative number");
        }

        this.age = age;
    }

    public void info() {
        System.out.println("id: " + id + "; Имя пользователя: " + name + "; Должность: " + position + "; Возраст: " + age);
    }

    public void info(String prefix) {
        System.out.println("Prefix: " + prefix + "id: " + id + "; Имя пользователя: " + name + "; Должность: " + position + "; Возраст: " + age);
    }

    public void changePosition(String position) {
        this.position = position;
        System.out.println("Пользователь " + name + " получил новую должность: " + position);
    }

    public static void main(String[] args) {
        User user = new User(1, "Test User", "Worker", 30);
        user.info();
    }
}
package dev.astamur.geekbrains.lessons.lesson2;

public class PlayWithFinally {
    public static void main(String[] args) {
        finallyPriority();
        finallyAndExit();
    }

    public static void finallyPriority() {
        try {
            System.out.println("In try");
            throw new RuntimeException("Oops!");
        } catch (Exception e) {
            System.out.println("In catch");
            throw e;
        } finally {
            System.out.println("In finally");
            // Это исключение победит
            throw new RuntimeException("Broken in finally");
        }
    }

    public static void finallyAndExit() {
        try {
            System.out.println("In try");
            System.exit(0); // Аналогично при какком-то системном Error-исключении
        } finally {
            System.out.println("In finally");

        }
    }
}

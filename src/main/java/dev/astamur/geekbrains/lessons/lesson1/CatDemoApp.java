package dev.astamur.geekbrains.lessons.lesson1;

public class CatDemoApp {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Барсик", "Коричневый", 4);
        Cat cat2 = new Cat("Мурзик", "Белый", 5);

        cat1.setName("Барсик");
        cat1.color = "Белый";
        cat1.age = 4;

        cat2.setName("Мурзик");
        cat2.color = "Черный";
        cat2.age = 6;

        System.out.println("Кот1 имя: " + cat1.getName() + " цвет: " + cat1.color + " возраст: " + cat1.age);
        System.out.println("Кот2 имя: " + cat2.getName() + " цвет: " + cat2.color + " возраст: " + cat2.age);

    }
}


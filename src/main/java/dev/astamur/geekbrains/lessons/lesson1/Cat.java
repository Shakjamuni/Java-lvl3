package dev.astamur.geekbrains.lessons.lesson1;

import dev.astamur.geekbrains.lessons.lesson1.first.Animal;
import dev.astamur.geekbrains.lessons.lesson1.first.Jumping;

public class Cat extends Animal implements Jumping {
    String color;
    int age;

    public Cat(String color, int age) {
        this.color = color;
        this.age = age;
    }

    public Cat(String name, String color, int age) {
        super(name);
        this.color = color;
        this.age = age;
    }

    public void catInfo() {
        System.out.printf("Кошка. Имя: %s. Цвет: %s. Возраст: %d\n", name, color, age);
    }

    @Override
    public void animalInfo() {
        catInfo();
    }

    @Override
    public void voice() {
        System.out.println("Cat: myau!");
    }

    @Override
    public void jump() {
        System.out.println("Cat: jump!");
    }
}
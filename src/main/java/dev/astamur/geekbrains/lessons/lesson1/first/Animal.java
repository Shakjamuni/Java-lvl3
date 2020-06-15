package dev.astamur.geekbrains.lessons.lesson1.first;

public abstract class Animal {
    protected String name;

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void animalInfo() {
        System.out.println("Животное: " + name);
    }

    public abstract void voice();

}

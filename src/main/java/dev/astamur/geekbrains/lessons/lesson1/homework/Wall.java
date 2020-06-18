package dev.astamur.geekbrains.lessons.lesson1.homework;

public class Wall implements Obstacle {
    private final double height;

    public Wall(double height) {
        this.height = height;
    }

    @Override
    public boolean perform(Athlete athlete) {
        return athlete.jump(height);
    }

    @Override
    public String toString() {
        return "Wall " + height;
    }
}

package dev.astamur.geekbrains.lessons.lesson1.homework;

public abstract class AbstractAthlete implements Athlete {
    protected final String name;
    protected final double maxRunDistance;
    protected final double maxJumpHeight;

    public AbstractAthlete(String name, double maxRunDistance, double maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public String getName() {
        return name;
    }
}

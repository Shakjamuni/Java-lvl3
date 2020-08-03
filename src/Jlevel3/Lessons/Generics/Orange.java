package Jlevel3.Lessons.Generics;

public class Orange extends Fruit {
    String name;
    private float weight;

    //конструктор с параметром на будущее, а так - 1.5f
    public Orange(String name) {
        this.weight = 1.5f;
        this.name = name;
    }
}

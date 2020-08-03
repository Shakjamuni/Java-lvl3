package Jlevel3.Lessons.Generics;

public class Apple extends Fruit{
    private float weight;
    //конструктор с параметром на будущее, а так - 1f
    String name;
    Apple(String name) {
        this.weight = 1f;
        this.name = name;
    }
}

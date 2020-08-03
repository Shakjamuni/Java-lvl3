package Jlevel3.Lessons.Generics;

import java.awt.desktop.AppForegroundListener;

public class GenericsTester {
    public static void main(String[] args) {
        Apple apple = new Apple("apple");
        Orange orange = new Orange("orange");
        FruitBox<Apple> appleBox = new FruitBox();
        FruitBox<Orange> orangeBox = new FruitBox();
// наполняем коробку яблок яблоками.
        for (int i = 0; i < 10; i++) {
            appleBox.addFruit(1, new Apple("apple " + i));
        }
// наполняем коробку апельсинов апельсинами.
        for (int i = 0; i < 10; i++) {
            orangeBox.addFruit(1, new Orange("name " + i));
        }
        // пытаемся добавить яблоки в коробку с апельсинами , ошибка компиляции.
//        orangeBox.addFruit(1, apple);


        System.out.println("Weight of appleBox " + appleBox.getWeightOfBox());
        System.out.println("Weight of orangeBox " + orangeBox.getWeightOfBox());
    }
}

package Jlevel3.Lessons.Generics;

import java.io.FileReader;
import java.util.ArrayList;

//b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
// поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
public class FruitBox<Fruit> {
    int counterOfFruitsInBox;

//    c. Для хранения фруктов внутри коробки можете использовать ArrayList;

    ArrayList<Fruit> contentsOfBox = new ArrayList<>();

    //d. Сделать метод getWeight() который высчитывает вес коробки,
    // зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
    public float getWeightOfBox() {
        if (contentsOfBox.get(0).getClass().getSimpleName().equals("Apple")) {
            return contentsOfBox.size() * 1.0f;
        } else {
            return contentsOfBox.size() * 1.5f;
        }
    }

    void addFruit(int countOfFruit, Fruit someKind) {
        counterOfFruitsInBox += countOfFruit;
        contentsOfBox.add(someKind);

    }

//f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
// (помним про сортировку фруктов,
// нельзя яблоки высыпать в коробку с апельсинами),
// соответственно в текущей коробке фруктов не остается,
// а в другую перекидываются объекты, которые были в этой коробке;
    void moveToAnotherBox(FruitBox fruitBox, FruitBox anotherFruitBox) {
        anotherFruitBox.addFruit(1, fruitBox.contentsOfBox.addAll(fruitBox.contentsOfBox));
        fruitBox.contentsOfBox.removeAll(fruitBox.contentsOfBox);
    }

    boolean compre(FruitBox box1, FruitBox box2) {
        if (box1.getWeightOfBox() == box2.getWeightOfBox()) {
            return true;
        } else {
            return false;
        }
    }
}

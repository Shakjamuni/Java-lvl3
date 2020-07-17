package Jlevel3.Lessons.Generics;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GenericsHomeWork {

    ArrayList<Object> arrayOfObjects;

    //    1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    void swapElements(Object[] array, int positionOld, int positionNew) {
        Object temp = array[positionOld];
        array[positionOld] = array[positionNew];
        array[positionNew] = array[positionOld];
    }

    //    2. Написать метод, который преобразует массив в ArrayList;
    ArrayList<Object> transformToArrayList(Object[] array) {
        for (Object obj : array) {
            arrayOfObjects.add(obj);
        }
        return arrayOfObjects;
    }

    //    3. Большая задача:


}

package dev.astamur.geekbrains.lessons.lesson3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
        arrayLists();
        linkedLists();
    }

    private static void arrayLists() {
        LinkedList<Integer> numbers = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            numbers.add(i);
        }

        System.out.println(numbers);

        Iterator<Integer> iterator  = numbers.iterator();
        while (iterator.hasNext()) {
            System.out.print(String.format("%s ", iterator.next()));
            iterator.remove();
        }
        System.out.println();
    }

    private static void linkedLists() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            numbers.add(i);
        }

        System.out.println(numbers);
        System.out.println(getCapacity(numbers));
    }

    private static <T> int getCapacity(List<T> list) {
        try {
            Field elementData = ArrayList.class.getDeclaredField("elementData");
            elementData.setAccessible(true);
            return ((Object[]) elementData.get(list)).length;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
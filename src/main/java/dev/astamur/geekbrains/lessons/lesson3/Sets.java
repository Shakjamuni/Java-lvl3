package dev.astamur.geekbrains.lessons.lesson3;

import java.util.*;

public class Sets {
    public static void main(String[] args) {
        hashSets();
        linkedHashSets();
        treeSets();
    }

    private static void hashSets() {
        Set<String> set = new HashSet<>();

        for (int i = 1; i <= 100; i++) {
            set.add("Value " + (i % 10));
        }

        System.out.println(set);
    }

    private static void linkedHashSets() {
        Set<String> set = new LinkedHashSet<>();

        for (int i = 1; i <= 100; i++) {
            set.add("Value " + (i % 10));
        }

        System.out.println(set);
    }

    private static void treeSets() {
        Comparator<Cat> catComparator = new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        NavigableSet<Cat> cats = new TreeSet<>(catComparator.reversed());

        for (int i = 1; i <= 12; i++) {
            cats.add(new Cat("Cat " + i, i));
        }

        System.out.println(cats);
    }
}

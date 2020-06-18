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

        for (int i = 1; i <= 12; i++) {
            set.add("Value " + i);
        }

        System.out.println(set);
    }

    private static void linkedHashSets() {
        Set<String> set = new LinkedHashSet<>();

        for (int i = 1; i <= 12; i++) {
            set.add("Value " + i);
        }

        System.out.println(set);
    }

    private static void treeSets() {
        NavigableSet<Cat> cats = new TreeSet<>();

        for (int i = 1; i <= 12; i++) {
            cats.add(new Cat("Cat " + i, i));
        }

        System.out.println(cats);
    }
}

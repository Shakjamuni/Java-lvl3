package dev.astamur.geekbrains.lessons.lesson3;

import java.lang.reflect.Field;
import java.util.*;

public class Maps {
    public static void main(String[] args) {
        hashMaps();
        treeMaps();
        linkedHashMaps();
    }

    private static void hashMaps() {
        Map<String, String> map = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            map.put("Key " + i, "Value " + i);
        }

        System.out.println(map);
        System.out.println(String.format("Size: %d. Capacity: %d. Load Factor: %f.", map.size(), getCapacity(map), getLoadFactor(map)));
    }

    private static void linkedHashMaps() {
        Map<String, String> map = new LinkedHashMap<>();

        for (int i = 1; i <= 12; i++) {
            map.put("Key " + i, "Value " + i);
        }

        System.out.println(map);
    }

    private static void treeMaps() {
        NavigableMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());

        for (int i = 1; i <= 5; i++) {
            map.put(i, "Value " + i);
        }

        System.out.println(map.ceilingEntry(3));
        System.out.println(map.subMap(4, 1));
    }

    private static <K, V> int getCapacity(Map<K, V> map) {
        try {
            Field table = HashMap.class.getDeclaredField("table");
            table.setAccessible(true);
            return ((Object[]) table.get(map)).length;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <K, V> float getLoadFactor(Map<K, V> map) {
        try {
            Field loadFactor = HashMap.class.getDeclaredField("loadFactor");
            loadFactor.setAccessible(true);
            return (float) loadFactor.get(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

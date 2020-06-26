package dev.astamur.geekbrains.lessons.lesson5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncExample {
    public static void main(String[] args) throws InterruptedException {
        // Counter counter = new Counter();
        AtomicInteger counter = new AtomicInteger();
        // Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                //counter.increment();
                // counter.incrementSyncMethod();
                counter.incrementAndGet();
                int rem = i % 10;
                map.merge(rem, 1, Integer::sum);
            }
            System.out.println("Task has finished. Thread " + Thread.currentThread().getName());
        };

        Thread one = new Thread(task);
        Thread two = new Thread(task);

        one.start();
        two.start();

        one.join();
        two.join();

        // System.out.println("Counter value: " + counter.getValue());
        System.out.println("Counter value: " + counter.get());
        System.out.println("Map: " + map);
    }

    public static class Counter {
        private long value;
        private final Object internalLock = new Object();

        public long getValue() {
            return value;
        }

        public void increment() {
            value++;
        }

        public synchronized void incrementSyncMethod() {
            // Действия не треб. синхр. ...
            value++;
            // Действия не треб. синхр. ...
        }

        public void incrementSyncBlock() {
            // Действия не треб. синхр. ...

            synchronized (internalLock) {
                value = value + 1;
            }

            // Действия не треб. синхр. ...
        }
    }
}

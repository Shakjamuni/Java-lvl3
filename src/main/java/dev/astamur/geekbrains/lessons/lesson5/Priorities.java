package dev.astamur.geekbrains.lessons.lesson5;

import java.math.BigInteger;

public class Priorities {
    public static void main(String[] args) throws InterruptedException {
        Counter counterA = new Counter();
        Counter counterB = new Counter();

        Thread threadA = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                counterA.increment();
            }
        });

        Thread threadB = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                counterB.increment();
            }
        });

        threadA.setPriority(Thread.MAX_PRIORITY);
        threadB.setPriority(Thread.MIN_PRIORITY);
        threadA.start();
        threadB.start();

        Thread.sleep(5_000);

        threadA.interrupt();
        threadB.interrupt();

        System.out.println("Counter A: " + counterA.get());
        System.out.println("Counter B: " + counterB.get());
    }

    private static class Counter {
        private long value = 0;

        public long get() {
            return value;
        }

        public void increment() {
            value++;
        }
    }
}
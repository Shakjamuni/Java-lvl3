package dev.astamur.geekbrains.lessons.lesson5.homework;

import java.util.Arrays;

public class Example {
    private static final int SIZE = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n\nОдин поток");
        execSameThread();

        System.out.println("\n\nДва потока");
        execSplit();
    }

    private static void execSameThread() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        long start = System.currentTimeMillis();
        new ExecTask(arr).run();
        System.out.printf("Общее время: %s мсек\n", System.currentTimeMillis() - start);

    }

    private static void execSplit() throws InterruptedException {
        float[] arr = new float[SIZE];
        float[] arrLeft = new float[SIZE / 2];
        float[] arrRight = new float[SIZE / 2];
        Arrays.fill(arr, 1.0f);

        long startTime = System.currentTimeMillis();
        long pointInTime = startTime;

        System.arraycopy(arr, 0, arrLeft, 0, SIZE / 2);
        System.arraycopy(arr, SIZE / 2, arrRight, 0, SIZE / 2);

        System.out.printf("Время разбивки: %d мсек\n", System.currentTimeMillis() - pointInTime);

        pointInTime = System.currentTimeMillis();
        Thread thread1 = new Thread(new ExecTask(arrLeft));
        Thread thread2 = new Thread(new ExecTask(arrRight));
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("Время обработки: %d мсек\n", System.currentTimeMillis() - pointInTime);

        pointInTime = System.currentTimeMillis();
        System.arraycopy(arrLeft, 0, arr, 0, SIZE / 2);
        System.arraycopy(arrRight, 0, arr, SIZE / 2, SIZE / 2);

        System.out.printf("Время склейки: %d мсек\n", System.currentTimeMillis() - pointInTime);
        System.out.printf("Общее время: %d мсек\n", System.currentTimeMillis() - startTime);

    }

    static class ExecTask implements Runnable {
        private final float[] arr;

        public ExecTask(float[] arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }
}
package dev.astamur.geekbrains.lessons.lesson3;

import java.util.Arrays;

public class ArraysExamples {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));

        // Хотим увеличить массив
        int[] arrNew = new int[10];
        System.arraycopy(arr, 0, arrNew, 0, arr.length);
        arr = arrNew;

        System.out.println(Arrays.toString(arr));
    }
}

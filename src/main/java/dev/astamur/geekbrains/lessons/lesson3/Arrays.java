package dev.astamur.geekbrains.lessons.lesson3;

public class Arrays {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(java.util.Arrays.toString(arr));

        // Хотим увеличить массив
        int[] arrNew = new int[10];
        System.arraycopy(arr, 0, arrNew, 0, arr.length);

        arr = arrNew;
        arrNew = null;

        System.out.println(java.util.Arrays.toString(arr));
    }
}

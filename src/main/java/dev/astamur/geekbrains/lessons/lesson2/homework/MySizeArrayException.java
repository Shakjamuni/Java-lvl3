package dev.astamur.geekbrains.lessons.lesson2.homework;

public class MySizeArrayException extends RuntimeException {
    public MySizeArrayException(int expectedSize, int actualSize) {
        super(String.format("Expected %d rows in matrix. Actual amount %d", expectedSize, actualSize));
    }

    public MySizeArrayException(int row, int expectedSize, int actualSize) {
        super(String.format("Expected row %d has length %d. Actual length %d", row, expectedSize, actualSize));
    }
}

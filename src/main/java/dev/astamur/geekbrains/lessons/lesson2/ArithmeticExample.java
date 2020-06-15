package dev.astamur.geekbrains.lessons.lesson2;

public class ArithmeticExample {
    public static void main(String[] args) {
        int[] numbers = new int[]{0, 1, 2, 3, 4, 5};
        System.out.println(calc(numbers));
        System.out.println(calcChecked(numbers));

        System.out.println("Программа завершена!");
    }

    public static int calc(int[] numbers) {
        int result = 1;

        for (int i = 0; i <= numbers.length; i++) {
            try {
                result = result / numbers[i];

            } catch (ArithmeticException e) {
                System.out.println("Деление на ноль! Result: " + result);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Вышли за пределы массива!");
            } catch (Throwable t) {
                System.out.println("Что-то пошло не так!");
            }
        }

        return result;
    }

    public static int calcChecked(int[] numbers) throws ArrayWithZeroException {
        for (int n : numbers) {
            if (n == 0) {
                throw new ArrayWithZeroException("В массиве есть 0!");
            }
        }

        int result = 1;

        for (int i = 0; i <= numbers.length; i++) {
            result = result / numbers[i];
        }

        return result;
    }
}

class ArrayWithZeroException extends RuntimeException {
    // Дополнительные поля в классе исключения

    public ArrayWithZeroException(String message) {
        super(message);
    }

    // Геттеры, сеттеры и т.д.
}

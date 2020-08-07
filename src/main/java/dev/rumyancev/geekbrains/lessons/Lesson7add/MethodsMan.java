package dev.rumyancev.geekbrains.lessons.Lesson7add;

public class MethodsMan {
    //    2. Написать метод, которому в качестве аргумента передается
    //    не пустой одномерный целочисленный массив.
    //    Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
    //    идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
    //    иначе в методе необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    //    Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
    static public int[] cutArray(int[] array) throws RuntimeException {
        int[] arrayForReturn = null;
        int lastIndex = array.length - 1;
        for (int i = lastIndex; i >= 0; i--) {
            if (array[i] == 4) {
                arrayForReturn = new int[i - 1];
                System.arraycopy(array, i, arrayForReturn, 0, i - 1);
                break;
            }
            if (i == 0) {
                throw new RuntimeException("Array does not contain number 4.");
            }
        }

        return arrayForReturn;
    }

    //3. Написать метод, который проверяет состав массива из чисел 1 и 4.
    // Если в нем нет хоть одной четверки или единицы,
    // то метод вернет false;
    // Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    static public boolean checkOneOrFour(int[] testArray) {
        for (int i : testArray) {
            if (i != 1 && i != 4) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] testArray = {1, 4, 1, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1};
        int[] test = cutArray(testArray);
        for (int i : test) {
            System.out.print(i + " ");
        }
        cutArray(testArray);
        System.out.println(checkOneOrFour(testArray));
    }
}

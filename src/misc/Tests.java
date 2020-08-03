package misc;

public class Tests {
    int[] array;
    int right;
    int left;
    int value;

    int method(int[] array, int left, int right, int value) {
        if (left >= right) {
            return 0;
        } else if (left == right - 1) {
            return (array[left] == value) ? 1 : 0;
        } else {
            int middle = (left + right) / 2;
            return method(array, left, middle, value)
                    + method(array, middle, right, value);
        }
    }

    public static void main(String[] args) {
        Tests tests = new Tests();
        int[] arr1 = {1, 2, 3, 5, 5};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 3, 3, 3};
        int[] arr4 = {2, 2, 2, 2, 2};
        int[] arr5 = {1, 1, 1, 2, 2};
        System.out.println(tests.method(arr1, 0, 5, 5));
        System.out.println(tests.method(arr2, 0, 4, 5));
        System.out.println(tests.method(arr3, 0, 4, 3));
        System.out.println(tests.method(arr4, 0, 4, 2));
        System.out.println(tests.method(arr5, 1, 3, 1));
    }
}

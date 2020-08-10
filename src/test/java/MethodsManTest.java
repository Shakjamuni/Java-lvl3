import dev.rumyancev.geekbrains.lessons.Lesson7add.MethodsMan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MethodsManTest {

    private MethodsMan mm = new MethodsMan();
    int[] testArray1 = {1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1};
    int[] testArray2 = {1, 4, 1, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1};
    int[] testArray3 = {1, 0, 1, 1, 1, 3, 2, 1, 1, 1, 1, 1, 1};
    int[] testArray4 = {1, 4, 1, 1, 1, 4, 4, 6, 1, 1, 1, 1, 1};
    RuntimeException re = new RuntimeException("Array does not contain number 4.");

    // тесты метода cutArray из MethodsMan
    //проверяем что вернется ненулевой массив.
    @Test
    void test1_1() {
        Assertions.assertNotEquals(null, mm.cutArray(testArray1));
    }

    //проверяем что вернется ненулевой массив.
    @Test
    void test1_2() {
        Assertions.assertNotEquals(null, mm.cutArray(testArray2));
    }

    //    //проверяем что выбросит по RuntimeException.
    @Test
    void test1_3() {
        Assertions.assertThrows(RuntimeException.class, () -> mm.cutArray(testArray3));
    }

    //проверяем что длина совпадает с определенным значением.
    @Test
    void test1_4() {
        int[] testArr;
        Assertions.assertEquals(5, mm.cutArray(testArray4).length);

    }

    // тесты метода checkOneOrFour из MethodsMan

    @Test
    void test2_1() {
        Assertions.assertEquals(true, mm.checkOneOrFour(testArray1));

    }

    @Test
    void test2_2() {
        Assertions.assertEquals(true, mm.checkOneOrFour(testArray2));

    }

    @Test
    void test2_3() {
        Assertions.assertEquals(false, mm.checkOneOrFour(testArray3));

    }

    @Test
    void test2_4() {
        Assertions.assertEquals(false, mm.checkOneOrFour(testArray4));
    }
}

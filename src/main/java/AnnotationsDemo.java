import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

//priority int в аннотациях - высший приоритет - 1.
@Retention(RetentionPolicy.RUNTIME)
@interface SelfAnnotation {
    String str();

    int priority();
}

@Retention(RetentionPolicy.RUNTIME)
@interface BeforeSuite {
    String str();

    int priority() default 1;
}

@Retention(RetentionPolicy.RUNTIME)
@interface AfterSuite {
    String str();

    int priority() default Integer.MAX_VALUE;
}

@Retention(RetentionPolicy.RUNTIME)
@interface Test {
    String str();

    int priority() default 1;
}

@SelfAnnotation(str = "main class", priority = 1)
public class AnnotationsDemo {

    static void start(AnnotationsDemo ademo) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = AnnotationsDemo.class.getDeclaredMethods();
        //счетчики встречающихся AfterSuite, BeforeSuite.
        int counterBefSui = 0;
        int counterAftSui = 0;
        //в этом Мар будет храниться отсоритрованный по приоритету в аннотациях набор тестов.
        TreeMap<Integer, Method> testsQueue = new TreeMap();

        //проходим по массиву в поисках повтора BeforeSuite или AfterSuite, бросаем Runtime в случае counter* > 1 а также наполняем
        //testQueue методами с @Test + в конце testQueue устанавливаем @AfterSuite.
        for (Method m : methods) {
            if (m.getAnnotation(BeforeSuite.class) != null) {
                counterBefSui++;
                if (counterBefSui > 1) {
                    throw new RuntimeException("There can be only 1 marker BeforeSuite in this class.");
                }
            }
            if (m.getAnnotation(AfterSuite.class) != null) {
                counterAftSui++;
                if (counterAftSui > 1) {
                    throw new RuntimeException("There can be only 1 marker BeforeSuite in this class.");
                } else {
                    testsQueue.put(m.getAnnotation(AfterSuite.class).priority(), m);
                }
            }
            if (m.getAnnotation(Test.class) != null) {
                testsQueue.put(m.getAnnotation(Test.class).priority(), m);
            }
        }
        for (Method m : methods) {
            if (m.getAnnotation(BeforeSuite.class) != null) {
                m.invoke(ademo);
                for (Map.Entry<Integer, Method> o : testsQueue.entrySet()) {
                    o.getValue().invoke(ademo);
                }
            }


        }
    }

    @BeforeSuite(str = "BeforeSuite", priority = 10)
    static void beforSuiteDemo() {
        System.out.println("Test with @BeforeSuite annotation");
    }


    @Test(str = "Test 1", priority = 5)
    static void test1() {
        System.out.println("test1 with priority 5");
    }

    @Test(str = "Test 2", priority = 7)
    static void test2() {
        System.out.println("test2 with priority 7");
    }

    @Test(str = "Test 3", priority = 9)
    static void test3() {
        System.out.println("test3 with priority 9");
    }

    @Test(str = "Test 4", priority = 2)
    static void test4() {
        System.out.println("test4 with priority 2");
    }

    @Test(str = "Test 2", priority = 3)
    static void test5() {
        System.out.println("test5 with priority 3");
    }

    @AfterSuite(str = "BeforeSuite", priority = 10)
    static void afterSuiteDemo() {
        System.out.println("Test with @AfterSuite annotation");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        AnnotationsDemo ademo = new AnnotationsDemo();
        start(ademo);

    }
}


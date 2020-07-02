package Lessons.Lesson5;

public class MultyThreadingJava implements Runnable {
    static final int size = 1_000_000;
    static final int h = size / 2;
    float[] array = new float[size];
    float[] halfOfArr = new float[h];
    float[] halfOfArr2 = new float[h];

    float[] arrayFillerWith1(float[] array) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            array[i] = 1f;
        }
        return array;
    }

    void arrayFillerWithMaths(float[] array) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время потраченное на расчеты с содержимым массива :" + (System.currentTimeMillis() - startTime) + " мс");
    }

    void arrayDivider(float[] array) {
        System.out.println("Внутри многопоточного метода.");
        long startTime = System.currentTimeMillis();
        System.arraycopy(array, 0, halfOfArr, 0, h);
        System.arraycopy(array, h, halfOfArr2, 0, h);
        System.out.println("Время разбивки на подмассивы:"+ (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        new Thread(() -> this.arrayFillerWithMaths(this.halfOfArr)).start();
        new Thread(() -> this.arrayFillerWithMaths(this.halfOfArr2)).start();

        startTime = System.currentTimeMillis();
        System.arraycopy(halfOfArr, 0, array, 0, h);
        System.arraycopy(halfOfArr2, 0, array, h, h);
        System.out.println("Время на склеивание массивов:"+ (System.currentTimeMillis() - startTime));

    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MultyThreadingJava mtj = new MultyThreadingJava();
        mtj.arrayFillerWith1(mtj.array);
        mtj.arrayFillerWithMaths(mtj.array);
        //перезаполняем массив
        mtj.arrayFillerWith1(mtj.array);
        //запускаем многопоточный метод
        mtj.arrayDivider(mtj.array);

    }
}

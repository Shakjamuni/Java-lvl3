package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    //1. барьер для одновременного вывода к старту Car[];
    static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);

    //2. семафор для запуска не более чем CARS_COUNT / 2 в Tunnel;
    static Semaphore sem = new Semaphore(CARS_COUNT / 2);

    //3. обратный счетчик для определения готовности всех Car[] и вывода сообщения "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"
    static CountDownLatch cdl = new CountDownLatch(CARS_COUNT);

    //4. обратный счетчик для определения окончания гонки, значение =  Car[].length * race.getStages.size();
    static CountDownLatch stageCounter;


    public static void main(String[] args) {
        Car[] cars = new Car[CARS_COUNT];
        Race race = new Race(new Road(60, cb), new Tunnel(cb, sem), new Road(40, cb));

        //инициализация 4-го пункта
        stageCounter = new CountDownLatch(cars.length * race.getStages().size());
        for (Stage s : race.getStages()) {
            s.stageCounter = stageCounter;
        }
        //;

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, cdl);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        //счетчик п.4 достиг нуля, конец гонки;
        if (stageCounter.getCount() == 0) {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }
}

class Car implements Runnable {

    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    CyclicBarrier cb;
    CountDownLatch cdl;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.cdl = cdl;
    }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            //в Main п.1 объект ждет остальные объекты Car на старте.
            cb.await();

            //в Main п.3 уменьшаем счетчик, если он == 0 начинаем гонку
            cdl.countDown();
            if (cdl.getCount() == 0) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            }
            //;
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

abstract class Stage {
    protected int length;
    protected String description;
    CyclicBarrier cb;
    CountDownLatch stageCounter;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length, CyclicBarrier cb) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.cb = cb;

    }

    @Override
    public void go(Car c) {
        try {
            //в Main п.1 объект ждет остальные объекты Car на Stage.
            cb.await();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            stageCounter.countDown();
            if (stageCounter.getCount() == 0) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    Semaphore sem;

    public Tunnel(CyclicBarrier cb, Semaphore sem) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.cb = cb;
        this.sem = sem;
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sem.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                sem.release();
                stageCounter.countDown();
                if (stageCounter.getCount() == 0) {
                    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
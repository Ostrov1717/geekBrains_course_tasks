package org.example.lesson7;

import lombok.Getter;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static final AtomicInteger winnerCount = new AtomicInteger(0);

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    @Getter
    private int speed;
    @Getter
    private String name;
    private CyclicBarrier cb;


    public Car(Race race, int speed, CyclicBarrier cb) {
        this.race = race;
        this.speed = speed;
        this.cb = cb;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            if (i == race.getStages().size() - 1) {
                if (winnerCount.compareAndSet(0, 1)) {
                    System.out.println(this.name + " - ПОБЕДИТЕЛЬ!!!");
                }
            }

        }
        try {
            cb.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


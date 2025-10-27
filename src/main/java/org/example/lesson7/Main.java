package org.example.lesson7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        semaphoreExample();
//        countDownLatchExample();
//        cyclicBarrierExample();
    }

    public static void semaphoreExample() {
        Semaphore smp = new Semaphore(1);
        for (int i = 0; i < 5; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + w + " перед семафором");
                    smp.acquire();
                    System.out.println("Поток " + w + " получил доступ к ресурсу");
                            Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Поток " + w + " освободил ресурс");
                    smp.release();
                }
            }).start();
        }
    }

public static void countDownLatchExample() {
    final int THREADS_COUNT = 6;
// задаем значение счетчика
    final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);
    System.out.println("Начинаем");
    for (int i = 0; i < THREADS_COUNT; i++) {
        final int w = i;
        new Thread(() -> {
            try {
// считаем, что выполнение задачи занимает ~1 сек
                System.out.println("Поток #" + w + " - старт");
                Thread.sleep(1000 + (int) (1000 * Math.random()));
                cdl.countDown();
// как только задача выполнена, уменьшаем счетчик
                System.out.println("Поток #" + w + " - готов");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    try {
        cdl.await();
// пока счетчик не приравняется нулю, будем стоять на этой строке
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("Работа завершена");
}

public static void cyclicBarrierExample() {
    CyclicBarrier cb = new CyclicBarrier(3);
    for (int i = 0; i < 3; i++) {
        final int w = i;
        new Thread(() -> {
            try {
                System.out.println("Поток " + w + " готовится");
                Thread.sleep(100 + (int) (3000 * Math.random()));
                System.out.println("Поток " + w + " готов");
                cb.await();
                System.out.println("Поток " + w + " запустился");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
}

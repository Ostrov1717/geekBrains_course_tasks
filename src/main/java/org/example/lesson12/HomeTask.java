package org.example.lesson12;

import jakarta.persistence.LockTimeoutException;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class HomeTask {
    private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hi_connections.cfg.xml")
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try (Session session = factory.openSession()) {
            //Fill
            session.beginTransaction();
            for (int i = 0; i < 40; i++) {
                Item item = new Item();
                item.setValue(0);
                session.save(item);
            }
            session.getTransaction().commit();

            //Work
            CyclicBarrier cb = new CyclicBarrier(9);
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Начинаем работу!!!");
            for (int i = 0; i < 8; i++) {
                new Thread(() -> incrementItems_2(cb)).start();
            }

            try {
                cb.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Работа закончилась!!!");

            //Calculate sum
            session.beginTransaction();
            long total = session.createQuery("SELECT sum(i.value) FROM Item i", Long.class)
                    .getSingleResult();
            session.getTransaction().commit();
            System.out.println("Total value: " + total);

        } finally {
            factory.close();
        }
    }

    private static void incrementItems(CyclicBarrier barrier) {
        long start = System.currentTimeMillis();
        try {
            for (int i = 1; i <= 10000; i++) {
                boolean updated = false;
                while (!updated) {
                    try (Session session = factory.openSession()) {
                        Transaction tx = session.beginTransaction();
                        Long randomId = ThreadLocalRandom.current().nextLong(1, 41);
                        Item item = session.get(Item.class, randomId);

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        item.setValue(item.getValue() + 1);
                        try {
                            tx.commit();
                            updated = true;
                        } catch (OptimisticLockException e) {
                            tx.rollback();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        updated = true;
                        i = 10001;
                    }
                }
            }
        } finally {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " finished in " + (System.currentTimeMillis() - start) + " ms");
                barrier.await();
            } catch (Exception ignored) { }
        }
    }

    private static void incrementItems_2(CyclicBarrier barrier) {
        long start = System.currentTimeMillis();
        try {
            for (int i = 1; i <= 10000; i++) {
                boolean success = false;
                while (!success) {
                    Transaction tx = null;
                    try (Session session = factory.openSession()) {
                        tx = session.beginTransaction();
                        Long randomId = ThreadLocalRandom.current().nextLong(1, 41);
                        Item item = session.get(Item.class, randomId, LockMode.PESSIMISTIC_WRITE);

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        item.setValue(item.getValue() + 1);
                        tx.commit();
                        success = true;

                    } catch (LockTimeoutException | JDBCException e) {
                        System.err.println("Поток " + Thread.currentThread().getName() + " словил deadlock/lock, повторяем. " + e.getMessage());
                        if (tx != null) {
                            tx.rollback();
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ignored) {   }
                    } catch (Exception e) {
                        if (tx != null) {
                            tx.rollback();
                        }
                        e.printStackTrace();
                        i = 2001;
                        success = true;
                    }
                }
            }
        } finally {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " finished in " + (System.currentTimeMillis() - start) + " ms");
                barrier.await();
            } catch (Exception ignored) {
            }
        }
    }
}

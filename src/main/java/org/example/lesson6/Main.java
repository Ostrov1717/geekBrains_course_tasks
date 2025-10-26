package org.example.lesson6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private final static int SIZE = 10_000_000;
    private final static int HALF = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {
        method1();
        method2();
        method2_efficient();
        method3_manualThreads();
        method3_executor();
    }

    public static void method1() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();
        incrementArray(arr, 0);
        long end = System.currentTimeMillis();
        System.out.println("Method1 time: " + (end - start) + " ms");
    }

    public static void method2() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

        Thread t1 = new Thread(() -> incrementArray(a1, 0));

        Thread t2 = new Thread(() -> incrementArray(a2, HALF));

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);

        long end = System.currentTimeMillis();
        System.out.println("Method2 time: " + (end - start) + " ms");
    }

    public static void method2_efficient() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> incrementArrayRange(arr, 0, HALF)); // Диапазон [0, HALF)
        Thread t2 = new Thread(() -> incrementArrayRange(arr, HALF, SIZE)); // Диапазон [HALF, SIZE)

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println("Method2 (efficient) time: " + (end - start) + " ms");
    }

    public static void method3_manualThreads() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        final int threadCount = Runtime.getRuntime().availableProcessors();
        System.out.println("Using " + threadCount + " threads.");

        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        final int chunkSize = SIZE / threadCount;

        for (int i = 0; i < threadCount; i++) {
            final int chunkStart = i * chunkSize;
            final int chunkEnd = (i == threadCount - 1) ? SIZE : (i + 1) * chunkSize;
            Thread t = new Thread(() -> incrementArrayRange(arr, chunkStart, chunkEnd));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Manual (" + threadCount + " threads) time: " + (end - start) + " ms");
    }

    public static void method3_executor() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        final int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        System.out.println("Using ExecutorService with " + threadCount + " threads.");
        long start = System.currentTimeMillis();

        final int chunkSize = SIZE / threadCount;

        for (int i = 0; i < threadCount; i++) {
            final int chunkStart = i * chunkSize;
            final int chunkEnd = (i == threadCount - 1) ? SIZE : (i + 1) * chunkSize;
            executor.submit(() -> incrementArrayRange(arr, chunkStart, chunkEnd));
        }

        executor.shutdown();

        if (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
            System.err.println("Tasks did not complete in time!");
        }

        long end = System.currentTimeMillis();
        System.out.println("ExecutorService time: " + (end - start) + " ms");
    }

    private static void incrementArray(float[] arr, int offset) {
//        System.out.println("Thread " + Thread.currentThread().getName() + " started");
        for (int i = 0; i < arr.length; i++) {
            int originalIndex = i + offset;
            arr[i] = (float) (arr[i] * Math.sin(0.2f + originalIndex / 5) * Math.cos(0.2f + originalIndex / 5) * Math.cos(0.4f + originalIndex / 2));
        }
//        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
    }

    private static void incrementArrayRange(float[] arr, int start, int end) {
//        System.out.println("Thread " + Thread.currentThread().getName() + " started range [" + start + ", " + end + ")");

        // Цикл идет от start до end
        for (int i = start; i < end; i++) {
            // 'i' уже является правильным глобальным индексом
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }
//        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
    }
}

package org.example.lesson9.timer;

public class AppService {

    @LogExecutionTime
    public void fastJob() throws InterruptedException {
        Thread.sleep(100);
    }

    @LogExecutionTime
    public void slowJob() throws InterruptedException {
        Thread.sleep(500);
    }

    public void noLogJob() throws InterruptedException {
        Thread.sleep(200);
    }
}

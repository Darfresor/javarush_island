package com.javarush.island.ostapenko.model.services.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class ModelThreadPoolManager {
    private final ExecutorService simulationCoreThread;
    private final ExecutorService feedingServiceThread;
    private final ExecutorService movementServiceThread;
    private final ExecutorService deathServiceThread;
    private final ExecutorService reproduceServiceThread;
    private final Phaser phaser;
    private Phaser currentTaskPhaser;

    public ModelThreadPoolManager(Phaser phaser) {
        this.phaser = phaser;
        this.simulationCoreThread = Executors.newSingleThreadExecutor();
        this.feedingServiceThread = Executors.newSingleThreadExecutor();
        this.movementServiceThread = Executors.newSingleThreadExecutor();
        this.deathServiceThread = Executors.newSingleThreadExecutor();
        this.reproduceServiceThread = Executors.newSingleThreadExecutor();
    }

    public void executeCoreTask(Runnable task) {
        phaser.register();
        simulationCoreThread.submit(() -> {
            try {
                currentTaskPhaser = new Phaser(1);
                task.run();
                currentTaskPhaser.arriveAndAwaitAdvance();
            } finally {
                phaser.arriveAndDeregister();
            }
        });
    }

    public void executeFeedTask(Runnable task) {
        currentTaskPhaser.register();
        feedingServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                currentTaskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeMoveTask(Runnable task) {
        currentTaskPhaser.register();
        movementServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                currentTaskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeDeathTask(Runnable task) {
        currentTaskPhaser.register();
        deathServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                currentTaskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeReproduceTask(Runnable task) {
        currentTaskPhaser.register();
        reproduceServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                currentTaskPhaser.arriveAndDeregister();
            }
        });
    }



    public void waitForAllTask() {
        phaser.arriveAndAwaitAdvance();
        System.out.println("Наконец дождались работы все потоков задач");
    }

    public void shutdown(){
        simulationCoreThread.shutdown();
        feedingServiceThread.shutdown();
        movementServiceThread.shutdown();
        deathServiceThread.shutdown();
        reproduceServiceThread.shutdown();
    }
}

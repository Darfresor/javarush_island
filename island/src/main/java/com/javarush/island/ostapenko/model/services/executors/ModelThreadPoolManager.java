package com.javarush.island.ostapenko.model.services.executors;

import com.javarush.island.ostapenko.core.util.Logger;

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

    private final ThreadLocal<Phaser> currentTaskPhaser = new ThreadLocal<>();

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
                currentTaskPhaser.set(new Phaser(1));
                task.run();
                currentTaskPhaser.get().arriveAndAwaitAdvance();
            } finally {
                phaser.arriveAndDeregister();
            }
        });
    }

    public void executeFeedTask(Runnable task) {
        Phaser taskPhaser = getCurrentTaskPhaser();
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }
        taskPhaser.register();

        feedingServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeMoveTask(Runnable task) {
        Phaser taskPhaser = getCurrentTaskPhaser();
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }

        taskPhaser.register();
        movementServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeDeathTask(Runnable task) {
        Phaser taskPhaser = getCurrentTaskPhaser();
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }
        taskPhaser.register();
        deathServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeReproduceTask(Runnable task) {
        Phaser taskPhaser = getCurrentTaskPhaser();
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }

        taskPhaser.register();
        reproduceServiceThread.submit(() -> {
            try {
                task.run();
            } finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }

    private Phaser getCurrentTaskPhaser() {
        Phaser phaser = currentTaskPhaser.get();
        if (phaser == null) {
            throw new IllegalStateException("Попытка выполнить задачу вне цикла симуляции");
        }
        return phaser;
    }

    public void waitForAllTask() {
        phaser.arriveAndAwaitAdvance();
        Logger.log("Наконец дождались работы всех потоков задач");
    }

    public void shutdown(){
        simulationCoreThread.shutdown();
        feedingServiceThread.shutdown();
        movementServiceThread.shutdown();
        deathServiceThread.shutdown();
        reproduceServiceThread.shutdown();
    }
}

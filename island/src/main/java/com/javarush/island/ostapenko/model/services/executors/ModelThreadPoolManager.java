package com.javarush.island.ostapenko.model.services.executors;

import com.javarush.island.ostapenko.core.util.Logger;

import java.util.concurrent.*;

public class ModelThreadPoolManager {
    private final ExecutorService simulationCoreThread;
    private final ExecutorService feedingServiceThread;
    private final ExecutorService movementServiceThread;
    private final ExecutorService deathServiceThread;
    private final ExecutorService reproduceServiceThread;
    private final Phaser phaser;

    private volatile Phaser currentCyclePhaser;

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
                currentCyclePhaser = new Phaser(1);
                task.run();
                currentCyclePhaser.arriveAndAwaitAdvance();
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                phaser.arriveAndDeregister();
            }
        });
    }

    public void executeFeedTask(Runnable task) {
        Phaser taskPhaser = currentCyclePhaser;
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }
        taskPhaser.register();

        feedingServiceThread.submit(() -> {
            try {
                task.run();
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeMoveTask(Runnable task) {
        Phaser taskPhaser = currentCyclePhaser;
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }

        taskPhaser.register();
        movementServiceThread.submit(() -> {
            try {
                task.run();
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeDeathTask(Runnable task) {
        Phaser taskPhaser = currentCyclePhaser;
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }
        taskPhaser.register();
        deathServiceThread.submit(() -> {
            try {
                task.run();
            } catch(Exception e){
                e.printStackTrace();
            }finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }

    public void executeReproduceTask(Runnable task) {
        Phaser taskPhaser = currentCyclePhaser;
        if (taskPhaser == null) {
            Logger.log("Ошибка: попытка выполнить задачу вне цикла");
            return;
        }

        taskPhaser.register();
        reproduceServiceThread.submit(() -> {
            try {
                task.run();
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                taskPhaser.arriveAndDeregister();
            }
        });
    }


    public void waitForAllTask() {
        try {
            int phase = phaser.arrive(); // Получаем текущую фазу
            phaser.awaitAdvanceInterruptibly(phase, 30, TimeUnit.SECONDS);
            Logger.log("Наконец дождались работы всех потоков задач");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.log("Прервано ожидание завершения цикла: " + e.getMessage());
        } catch (TimeoutException e) {
            Logger.log("⚠️ ТАЙМАУТ: Цикл не завершился за 30 секунд!");
            dumpThreadStacks();
        }

    }

    private void dumpThreadStacks() {
        System.err.println("=== ДАМП ПОТОКОВ ДЛЯ АНАЛИЗА DEADLOCK ===");
        Thread.getAllStackTraces().forEach((thread, stack) -> {
            if (thread.getName().contains("pool")) {
                System.err.println("Поток: " + thread.getName() + " - " + thread.getState());
                for (StackTraceElement element : stack) {
                    System.err.println("  " + element);
                }
                System.err.println("---");
            }
        });
    }

    public void shutdown(){
        simulationCoreThread.shutdown();
        feedingServiceThread.shutdown();
        movementServiceThread.shutdown();
        deathServiceThread.shutdown();
        reproduceServiceThread.shutdown();
    }
}

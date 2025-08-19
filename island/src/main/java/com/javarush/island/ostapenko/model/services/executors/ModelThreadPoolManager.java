package com.javarush.island.ostapenko.model.services.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModelThreadPoolManager {
    private final ExecutorService feedingServiceThread = Executors.newSingleThreadExecutor();
    private final ExecutorService movementServiceThread = Executors.newSingleThreadExecutor();
    private final ExecutorService deathServiceThread = Executors.newSingleThreadExecutor();
    private final ExecutorService reproduceServiceThread = Executors.newSingleThreadExecutor();

    public void executeFeedTask(Runnable task){
        feedingServiceThread.submit(task);
    }
    public void executeMoveTask(Runnable task){
        movementServiceThread.submit(task);
    }
    public void executeDeathTask(Runnable task){
        deathServiceThread.submit(task);
    }
    public void executeReproduceTask(Runnable task){
        reproduceServiceThread.submit(task);
    }
}

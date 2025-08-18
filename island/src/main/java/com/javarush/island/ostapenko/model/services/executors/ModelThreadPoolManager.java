package com.javarush.island.ostapenko.model.services.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModelThreadPoolManager {
    private final ExecutorService feedingServiceThread = Executors.newSingleThreadExecutor();
    private final ExecutorService movementServiceThread = Executors.newSingleThreadExecutor();

    public void executeFeedTask(Runnable task){
        feedingServiceThread.submit(task);
    }
    public void executeMoveTask(Runnable task){
        movementServiceThread.submit(task);
    }
}

package com.javarush.island.ostapenko.util;

import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.island.Cell;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final static boolean DEBUG = true;
    private static final List<String> logBuffer = new ArrayList<>();
    private static final int BUFFER_LIMIT = 1_000;

    private Logger() {
    }

    ;

    public static void log(String message) {
        if (DEBUG) {
            synchronized (logBuffer) {
                if (logBuffer.size() >= BUFFER_LIMIT) {
                    flush();
                }
                logBuffer.add(message);
            }
        }
    }

    public static void logService(Creature creature, Cell cell, String message){
        log(String.format("Thread-%s, ячейка-[%d %d]: [%s %d]  %s",
                Thread.currentThread().getName(),
                cell.getX(),
                cell.getY(),
                creature.getClass().getSimpleName(),
                System.identityHashCode(creature),
                message));
    }
    public static void logService(Creature creature, Cell cell, String message, String serviceName){
        log(String.format("(%s) || Thread-%s, ячейка-[%d %d]: [%s %d]  %s",
                serviceName,
                Thread.currentThread().getName(),
                cell.getX(),
                cell.getY(),
                creature.getClass().getSimpleName(),
                System.identityHashCode(creature),
                message));
    }
    public static void logMovementService(Creature creature, Cell cell, String message){
        logService(creature,cell, message, "MovementService");
    }
    public static void logDeathService(Creature creature, Cell cell, String message){
        logService(creature,cell, message, "DeathService");
    }

    public static void flush() {
        synchronized (logBuffer){
            if(!logBuffer.isEmpty()){
                System.out.println("=== Логирование ====");
                logBuffer.forEach(System.out::println);
                System.out.println("====================");
                logBuffer.clear();
            }
        }
    }

    public static List<String> getLogs(){
        synchronized (logBuffer){
            return new ArrayList<>(logBuffer);
        }
    }

    public static void clear(){
        synchronized (logBuffer){
            logBuffer.clear();
        }
    }
}

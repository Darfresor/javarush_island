package com.javarush.island.ostapenko.core.util;

import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Logger {
    private final static boolean DEBUG = true;
    private static final List<String> logBuffer = new ArrayList<>();
    private static final int BUFFER_LIMIT = 1_000;
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

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


    public static void logService(Creature creature, Cell cell, String message, String serviceName){
        log(String.format(" %s :(%s) || Thread-%s, ячейка-[%d %d]: [%s %d]  %s",
                LocalTime.now().format(TIME_FORMATTER),
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
    public static void logReproductionService(Creature creature, Cell cell, String message){
        logService(creature,cell, message, "ReproductionService");
    }
    public static void logFeedingService(Creature creature, Cell cell, String message){
        logService(creature,cell, message, "FeedingService");
    }
    public static void logIslandComposition(Island island){
        for (int i = 0; i < island.getGridCopy().length; i++) {
            for (int j = 0; j < island.getGridCopy()[0].length; j++) {
                if(island.getGridCopy()[i][j]!=null){
                    Collection<Animal> listAnimal = island.getGridCopy()[i][j].getAnimals();
                    log(String.format("Клетка [%d:%d] содержит животных: %s",i, j
                            ,listAnimal
                    ));
                }else{
                    log(String.format("Клетка [%d:%d] без животных",i, j));
                }

            }
        }
        for (int i = 0; i < island.getGridCopy().length; i++) {
            for (int j = 0; j < island.getGridCopy()[0].length; j++) {
                if(island.getGridCopy()[i][j]!=null){
                    Collection<Plant> listPlant = island.getGridCopy()[i][j].getPlants();
                    log(String.format("Клетка [%d:%d] содержит растения: %s",i, j
                            ,listPlant));
                }else{
                    log(String.format("Клетка [%d:%d] без растений",i, j));
                }

            }
        }

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

package com.javarush.island.ostapenko.model.services.statictics;

import com.javarush.island.ostapenko.core.dto.SimulationStatistics;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.event.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class StatisticsService implements IEventHandler {
    private final ConcurrentHashMap<String, AtomicLong> statistics = new ConcurrentHashMap<>();


    public void increment(String key) {
        statistics.computeIfAbsent(key, k -> new AtomicLong()).incrementAndGet();
    }

    public void decrement(String key) {
        statistics.computeIfAbsent(key, k -> new AtomicLong()).decrementAndGet();
    }

    public long add(String key, Long valueToAdd) {
        return statistics.computeIfAbsent(key, k -> new AtomicLong(0)).addAndGet(valueToAdd);
    }

    public void printStatistics() {
        System.out.println("=== СТАТИСТИКА ===");
        statistics.forEach((key, value) ->
                System.out.printf("%s %d %n", key, value.get())
        );
        System.out.println("===================");
    }

    public void intializeFromIsland(Island island) {
        island.getAnimalCountBySpecies().forEach((species, count) -> {
            set(species + ".current.count: ", count);
            add("total.animals: ", count);
        });
        island.getPlantCountBySpecies().forEach((species, count) -> {
            set(species + ".current.count: ", count);
            add("total.plants: ", count);
        });
    }
    public SimulationStatistics getSimulationStatistics(){
        return new SimulationStatistics(0,statistics.get("total.animals: ").get(),statistics.get("total.plants: ").get());
    }

    public void set(String key, long value) {
        statistics.put(key, new AtomicLong(value));
    }

    @Override
    public void handle(Event event) {
        switch (event) {
            case AnimalDeathByOld e -> {
                decrement(e.getAnimal().getClass().getSimpleName() + ".current.count: ");
                decrement("total.animals: ");

            }
            case PlantDeathByOld e -> {
                decrement(e.getPlant().getClass().getSimpleName() + ".current.count: ");
                decrement("total.plants: ");

            }
            case AnimalEatenEvent e -> {
                decrement(e.getVictim().getClass().getSimpleName() + ".current.count: ");
                decrement("total.animals: ");

            }
            case AnimalStarvationEvent e -> {
                decrement(e.getAnimal().getClass().getSimpleName() + ".current.count: ");
                decrement("total.animals: ");
            }
            case PlantEatenEvent e -> {
                decrement(e.getPlant().getClass().getSimpleName() + ".current.count: ");
                decrement("total.plants: ");
            }
            case null -> throw new RuntimeException("Event cannot be null");
            default -> throw new RuntimeException("Unknown event: " + event.getClass());
        }
    }
}

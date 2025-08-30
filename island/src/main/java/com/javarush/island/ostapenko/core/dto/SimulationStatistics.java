package com.javarush.island.ostapenko.core.dto;

public class SimulationStatistics {
    private final long currentDay;
    private final long totalAnimal;
    private final long totalPlants;

    public SimulationStatistics(long currentDay, long totalAnimal, long totalPlants) {
        this.currentDay = currentDay;
        this.totalAnimal = totalAnimal;
        this.totalPlants = totalPlants;
    }

    public long getCurrentDay() {
        return currentDay;
    }

    public long getTotalAnimal() {
        return totalAnimal;
    }

    public long getTotalPlants() {
        return totalPlants;
    }
}

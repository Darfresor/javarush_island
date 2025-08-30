package com.javarush.island.ostapenko.core.dto;

public class SimulationStatistics {
    private final int currentDay;
    private final int totalAnimal;
    private final int totalPlants;

    public SimulationStatistics(int currentDay, int totalAnimal, int totalPlants) {
        this.currentDay = currentDay;
        this.totalAnimal = totalAnimal;
        this.totalPlants = totalPlants;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getTotalAnimal() {
        return totalAnimal;
    }

    public int getTotalPlants() {
        return totalPlants;
    }
}

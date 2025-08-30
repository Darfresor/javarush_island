package com.javarush.island.ostapenko.core.dto;

import java.util.HashMap;
import java.util.Map;

public class SimulationStatistics {
    private final long currentDay;
    private final long totalAnimal;
    private final long totalPlants;
    private final Map<String,Long> detailStatistics;

    public SimulationStatistics(long currentDay, long totalAnimal, long totalPlants, Map<String,Long> detailStatistics) {
        this.currentDay = currentDay;
        this.totalAnimal = totalAnimal;
        this.totalPlants = totalPlants;
        this.detailStatistics = detailStatistics;
    }

    public Map<String, Long> getDetailStatistics() {
        return detailStatistics;
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

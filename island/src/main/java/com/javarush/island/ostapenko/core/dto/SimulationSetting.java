package com.javarush.island.ostapenko.core.dto;

import com.javarush.island.ostapenko.constants.GenerateCreatureType;

public class SimulationSetting {
    private final int numOfCellX;
    private final int getNumOfCellY;
    private final int simulationSpeedMs;
    private final boolean isDebug;
    private final GenerateCreatureType generateCreatureType;

    public SimulationSetting(int numOfCellX, int getNumOfCellY, int simulationSpeedMs,
                             boolean isDebug, GenerateCreatureType generateCreatureType) {
        this.numOfCellX = numOfCellX;
        this.getNumOfCellY = getNumOfCellY;
        this.simulationSpeedMs = simulationSpeedMs;
        this.isDebug = isDebug;
        this.generateCreatureType = generateCreatureType;
    }

    public int getNumOfCellX() {
        return numOfCellX;
    }

    public int getGetNumOfCellY() {
        return getNumOfCellY;
    }

    public int getSimulationSpeedMs() {
        return simulationSpeedMs;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public GenerateCreatureType getGenerateCreatureType() {
        return generateCreatureType;
    }
}

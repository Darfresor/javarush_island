package com.javarush.island.ostapenko.core.observer;

import com.javarush.island.ostapenko.core.dto.SimulationStatistics;

public interface IStatisticObserver {
    void onStaticticsUpdate(SimulationStatistics simulationStatistics);
}

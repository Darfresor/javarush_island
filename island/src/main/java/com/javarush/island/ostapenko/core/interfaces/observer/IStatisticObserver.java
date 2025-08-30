package com.javarush.island.ostapenko.core.interfaces.observer;

import com.javarush.island.ostapenko.core.dto.SimulationStatistics;

public interface IStatisticObserver {
    void onStaticticsUpdate(SimulationStatistics simulationStatistics);
}

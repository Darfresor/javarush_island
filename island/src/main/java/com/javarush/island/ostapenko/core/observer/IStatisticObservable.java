package com.javarush.island.ostapenko.core.observer;

import com.javarush.island.ostapenko.core.dto.SimulationStatistics;

public interface IStatisticObservable {
    void addStatisticListener(IStatisticObserver listener);
    void notifyStatisticListener(SimulationStatistics simulationStatistics);
}

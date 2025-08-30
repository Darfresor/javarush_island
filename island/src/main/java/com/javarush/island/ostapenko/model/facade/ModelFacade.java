package com.javarush.island.ostapenko.model.facade;

import com.javarush.island.ostapenko.core.dto.ModelRequest;
import com.javarush.island.ostapenko.core.dto.ModelResponse;
import com.javarush.island.ostapenko.core.dto.SimulationStatistics;
import com.javarush.island.ostapenko.core.interfaces.IModelFacade;
import com.javarush.island.ostapenko.core.interfaces.observer.IStatisticObservable;
import com.javarush.island.ostapenko.core.interfaces.observer.IStatisticObserver;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.generation.AnimalPopulationService;
import com.javarush.island.ostapenko.model.services.generation.IslandGenerationService;
import com.javarush.island.ostapenko.model.services.generation.PlantPopulationService;
import com.javarush.island.ostapenko.model.services.simulation.SimulationExecutionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ModelFacade implements IModelFacade, IStatisticObservable {
    private List<IStatisticObserver> statisticObservers = new ArrayList<>();
    private SimulationExecutionService simulationExecutionService;

    @Override
    public ModelResponse processSimulation(ModelRequest request) {
        Island island = new IslandGenerationService().generate();
        new AnimalPopulationService(island).generate();
        new PlantPopulationService(island).generate();


        simulationExecutionService = new SimulationExecutionService(island, this);
        simulationExecutionService.start(0, 2, TimeUnit.SECONDS);

        return null;
    }

    @Override
    public void stopSimulation() {
        simulationExecutionService.stop();
    }

    @Override
    public void addStatisticListener(IStatisticObserver listener) {
        statisticObservers.add(listener);
    }

    @Override
    public void notifyStatisticListener(SimulationStatistics simulationStatistics) {
        statisticObservers.forEach(listener->listener.onStaticticsUpdate(simulationStatistics));
    }
}

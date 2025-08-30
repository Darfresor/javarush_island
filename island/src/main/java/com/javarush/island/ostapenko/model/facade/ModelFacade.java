package com.javarush.island.ostapenko.model.facade;

import com.javarush.island.ostapenko.constants.GenerateCreatureType;
import com.javarush.island.ostapenko.core.dto.ModelRequest;
import com.javarush.island.ostapenko.core.dto.ModelResponse;
import com.javarush.island.ostapenko.core.dto.SimulationSetting;
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
        int numOfCellX = request.getData(SimulationSetting.class).getNumOfCellX();
        int getNumOfCellY = request.getData(SimulationSetting.class).getGetNumOfCellY();
        GenerateCreatureType generateCreatureType = request.getData(SimulationSetting.class).getGenerateCreatureType();
        int simulationSpeedMs = request.getData(SimulationSetting.class).getSimulationSpeedMs();

        Island island = new IslandGenerationService(numOfCellX, getNumOfCellY).generate(generateCreatureType);
        simulationExecutionService = new SimulationExecutionService(island, this);
        simulationExecutionService.start(0, simulationSpeedMs, TimeUnit.MILLISECONDS);

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

package com.javarush.island.ostapenko.model.facade;

import com.javarush.island.ostapenko.core.dto.ModelRequest;
import com.javarush.island.ostapenko.core.dto.ModelResponse;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.generation.AnimalPopulationService;
import com.javarush.island.ostapenko.model.services.generation.IslandGenerationService;
import com.javarush.island.ostapenko.model.services.generation.PlantPopulationService;
import com.javarush.island.ostapenko.model.services.simulation.SimulationExecutionService;

import java.util.concurrent.TimeUnit;

public class ModelFacade implements IModelFacade{
private SimulationExecutionService simulationExecutionService;
    @Override
    public ModelResponse processSimulation(ModelRequest request) {
        Island island = new IslandGenerationService().generate();
        new AnimalPopulationService(island).generate();
        new PlantPopulationService(island).generate();


        simulationExecutionService = new SimulationExecutionService(island);
        simulationExecutionService.start(0,2, TimeUnit.SECONDS);

        return null;
    }

    @Override
    public void stopSimulation() {
        simulationExecutionService.stop();
    }
}

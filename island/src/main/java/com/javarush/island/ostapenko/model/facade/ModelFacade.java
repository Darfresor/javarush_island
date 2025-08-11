package com.javarush.island.ostapenko.model.facade;

import com.javarush.island.ostapenko.dto.ModelRequest;
import com.javarush.island.ostapenko.dto.ModelResponse;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.generation.AnimalPopulationService;
import com.javarush.island.ostapenko.model.services.generation.IslandGenerationService;
import com.javarush.island.ostapenko.model.services.generation.PlantPopulationService;
import com.javarush.island.ostapenko.model.services.simulation.SimulationExecutionService;

public class ModelFacade implements IModelFacade{

    @Override
    public ModelResponse processSimulation(ModelRequest request) {
        Island island = new IslandGenerationService().generate();
        new AnimalPopulationService(island).generate();
        new PlantPopulationService(island).generate();

        SimulationExecutionService simulationExecutionService = new SimulationExecutionService(island);
        simulationExecutionService.start();


        return null;
    }
}

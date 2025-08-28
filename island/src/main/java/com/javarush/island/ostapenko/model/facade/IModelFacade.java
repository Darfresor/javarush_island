package com.javarush.island.ostapenko.model.facade;

import com.javarush.island.ostapenko.dto.ModelRequest;
import com.javarush.island.ostapenko.dto.ModelResponse;

public interface IModelFacade {
    ModelResponse processSimulation(ModelRequest request);
    void stopSimulation();
}

package com.javarush.island.ostapenko.tests;

import com.javarush.island.ostapenko.dto.ModelRequest;
import com.javarush.island.ostapenko.model.facade.IModelFacade;
import com.javarush.island.ostapenko.model.facade.ModelFacade;

public class IslandTest {
    public static void main(String[] args) {
        ModelRequest request = new ModelRequest("тест");
        IModelFacade model = new ModelFacade();
        model.processSimulation(request);
    }
}

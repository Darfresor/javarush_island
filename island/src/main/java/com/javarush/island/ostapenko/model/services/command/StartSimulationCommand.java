package com.javarush.island.ostapenko.model.services.command;


import com.javarush.island.ostapenko.dto.CommandResponse;
import com.javarush.island.ostapenko.dto.ModelRequest;
import com.javarush.island.ostapenko.model.facade.IModelFacade;

public class StartSimulationCommand implements ICommand{
private final IModelFacade model;

    public StartSimulationCommand(IModelFacade model) {
        this.model = model;
    }

    @Override
    public CommandResponse execute() {
        System.out.println("Создана команда запускающая симуляцию острова");
        ModelRequest request = new ModelRequest("тест");
        model.processSimulation(request);
        return null;
    }

}

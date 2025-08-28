package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.dto.CommandResponse;
import com.javarush.island.ostapenko.model.facade.IModelFacade;

public class StopSimulationCommand implements ICommand{
    private final IModelFacade model;

    public StopSimulationCommand(IModelFacade model) {
        this.model = model;
    }

    @Override
    public CommandResponse execute() {
        System.out.println("Создана команда для остановки симуляции острова");
        model.stopSimulation();
        return null;
    }

}

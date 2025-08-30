package com.javarush.island.ostapenko.model.services.command;


import com.javarush.island.ostapenko.core.dto.CommandResponse;
import com.javarush.island.ostapenko.core.dto.ModelRequest;
import com.javarush.island.ostapenko.core.dto.SimulationSetting;
import com.javarush.island.ostapenko.core.interfaces.IModelFacade;

public class StartSimulationCommand implements ICommand{
private final IModelFacade model;
private final SimulationSetting simulationSetting;

    public StartSimulationCommand(IModelFacade model, SimulationSetting simulationSetting) {
        this.model = model;
        this.simulationSetting =simulationSetting;
    }

    @Override
    public CommandResponse execute() {
        System.out.println("Создана команда запускающая симуляцию острова");
        model.processSimulation(new ModelRequest(simulationSetting));
        return null;
    }

}

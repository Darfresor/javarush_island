package com.javarush.island.ostapenko.model.services.command;


import com.javarush.island.ostapenko.core.dto.SimulationSetting;
import com.javarush.island.ostapenko.core.exception.ApplicationException;
import com.javarush.island.ostapenko.core.interfaces.IModelFacade;
import com.javarush.island.ostapenko.core.interfaces.IViewFacade;

public class CommandFactory {
    private CommandFactory() {
    }

    public static ICommand createCommand(IViewFacade view, IModelFacade model) {
        return switch (view.getParametrs().getCommandType()) {
            case START_SUMULATION ->
                    new StartSimulationCommand(model, view.getParametrs().getData(SimulationSetting.class));
            case STOP_SIMULATION -> new StopSimulationCommand(model);
            case null -> throw new ApplicationException("Command can not be null");
            default -> new UnsupportedCommand();
        };
    }
}

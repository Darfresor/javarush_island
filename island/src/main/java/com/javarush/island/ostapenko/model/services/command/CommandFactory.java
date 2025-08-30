package com.javarush.island.ostapenko.model.services.command;


import com.javarush.island.ostapenko.core.exception.ApplicationException;
import com.javarush.island.ostapenko.model.facade.IModelFacade;
import com.javarush.island.ostapenko.view.IViewFacade;

public class CommandFactory {
    private CommandFactory() {
    }

    public static ICommand createCommand(IViewFacade view, IModelFacade model){
    return switch(view.getParametrs().getCommandType()){
        case START_SUMULATION -> new StartSimulationCommand(model);
        case STOP_SIMULATION -> new StopSimulationCommand(model);
        case null -> throw new ApplicationException("Command can not be null");
        default -> new UnsupportedCommand();
    };
    }
}

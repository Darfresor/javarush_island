package com.javarush.island.ostapenko.model.services.command;


import com.javarush.island.ostapenko.exception.ApplicationException;
import com.javarush.island.ostapenko.view.IViewFacade;

public class CommandFactory {
    private CommandFactory() {
    }

    public static ICommand createCommand(IViewFacade view){
    return switch(view.getParametrs().getCommandType()){
        case START_SUMULATION -> new StartSimulationCommand();
        case STOP_SIMULATION -> new StopSimulationCommand();
        case null -> throw new ApplicationException("Command can not be null");
        default -> new UnsupportedCommand();
    };
    }
}

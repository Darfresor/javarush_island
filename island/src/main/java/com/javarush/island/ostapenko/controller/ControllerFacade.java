package com.javarush.island.ostapenko.controller;

import com.javarush.island.ostapenko.model.services.command.CommandFactory;
import com.javarush.island.ostapenko.model.services.command.ICommand;
import com.javarush.island.ostapenko.view.IViewFacade;

public class ControllerFacade implements IControllerFacade{
    private final IViewFacade IViewFacade;

    public ControllerFacade(IViewFacade IViewFacade) {
        this.IViewFacade = IViewFacade;
    }


    @Override
    public void initializeCommandHandler(){
        IViewFacade.setupEventHandlers(this::run);
    }

    @Override
    public void run() {
        ICommand command = CommandFactory.createCommand(IViewFacade);
        command.execute();
    }
}

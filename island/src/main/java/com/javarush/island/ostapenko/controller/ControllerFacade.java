package com.javarush.island.ostapenko.controller;

import com.javarush.island.ostapenko.model.facade.IModelFacade;
import com.javarush.island.ostapenko.model.services.command.CommandFactory;
import com.javarush.island.ostapenko.model.services.command.ICommand;
import com.javarush.island.ostapenko.view.IViewFacade;

public class ControllerFacade implements IControllerFacade{
    private final IViewFacade view;
    private final IModelFacade model;

    public ControllerFacade(IViewFacade view, IModelFacade model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void initializeCommandHandler(){
        view.setupEventHandlers(this::run);
    }

    @Override
    public void run() {
        ICommand command = CommandFactory.createCommand(view, model);
        command.execute();
    }
}

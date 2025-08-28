package com.javarush.island.ostapenko.controller;

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
        System.out.println(IViewFacade.getParametrs().getCommandType());
    }
}

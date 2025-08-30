package com.javarush.island.ostapenko.controller;

import com.javarush.island.ostapenko.core.dto.SimulationStatistics;
import com.javarush.island.ostapenko.core.exception.ApplicationException;
import com.javarush.island.ostapenko.core.observer.IStatisticObserver;
import com.javarush.island.ostapenko.model.facade.IModelFacade;
import com.javarush.island.ostapenko.model.facade.ModelFacade;
import com.javarush.island.ostapenko.model.services.command.CommandFactory;
import com.javarush.island.ostapenko.model.services.command.ICommand;
import com.javarush.island.ostapenko.view.IViewFacade;

public class ControllerFacade implements IControllerFacade, IStatisticObserver {
    private final IViewFacade view;
    private final IModelFacade model;

    public ControllerFacade(IViewFacade view, IModelFacade model) {
        this.view = view;
        this.model = model;
        initializeObserver(model);
    }

    private void initializeObserver(IModelFacade model){
        switch(model){
            case ModelFacade m -> m.addStatisticListener(this);
            case null -> throw new ApplicationException("Модель не может быть пустой");
            default -> throw new ApplicationException("Наблюдатели не могут подписывать на этот класс модели");
        }
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

    @Override
    public void onStaticticsUpdate(SimulationStatistics simulationStatistics) {
        System.out.println("Наблюдаемый объект сообщил контроллеру о своих изменениях в статистике");
    }
}

package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveForEatEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveForReproduceEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;
import com.javarush.island.ostapenko.util.Logger;


public class MovementService implements IEventHandler {
    private final IMediator mediator;
    private final ModelThreadPoolManager modelThreadPoolManager;

    public MovementService(IMediator mediator, ModelThreadPoolManager modelThreadPoolManager) {
        this.mediator = mediator;
        this.modelThreadPoolManager = modelThreadPoolManager;
    }

    public void executeMove(Animal animal, Cell currentCell, Island island, Event event) {
        try {
            Animal currentaAnimal =currentCell.getAnimalById(animal.getId());
            if (currentaAnimal != null && !currentaAnimal.isBeingEaten()) {
                Moveable strategy = BehavorFactory.createMoveStrategy(animal, mediator);
                strategy.move(animal, currentCell, island, event, modelThreadPoolManager);
            }
        }finally{
            Logger.flush();
        }

    }

    @Override
    public void handle(Event event) {
        switch(event){
            case AnimalMoveForEatEvent e-> executeMove(e.getAnimal(), e.getCurrentCell(), e.getIsland(), e);
            case AnimalMoveForReproduceEvent e-> executeMove(e.getAnimal(), e.getCurrentCell(), e.getIsland(), e);
            case null -> throw new RuntimeException("Event cannot be null");
            default -> throw new RuntimeException("Unknown event: " + event.getClass());
        }
    }
}

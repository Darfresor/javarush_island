package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.behavor.BehavorStrategyFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;

import java.util.UUID;

public class FeedingService implements IEventHandler {
    private final IMediator mediator;
    private final ModelThreadPoolManager modelThreadPoolManager;

    public FeedingService(IMediator mediator, ModelThreadPoolManager modelThreadPoolManager) {
        this.mediator = mediator;
        this.modelThreadPoolManager = modelThreadPoolManager;
    }

    public void executeEat(Island island) {
        Logger.log("FeedingService: STARTED");
        try{
        for (Cell[] cellVertical : island.getGridCopy()) {
                for (Cell cellHorizontal : cellVertical) {
                    if (cellHorizontal != null) {
                        for (UUID animalId : cellHorizontal.getAnimalIds()) {
                            Animal animal = cellHorizontal.getAnimalById(animalId);
                            if (animal != null && !animal.isBeingEaten()) {
                                Eatable strategy = BehavorStrategyFactory.createEatStrategy(animal, mediator);
                                strategy.eat(animal, cellHorizontal, island, modelThreadPoolManager);
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Logger.log("FeedingService: FINISHED");
    }

    public void executeEat(Animal animal, Cell cell, Island island) {
        Logger.log("FeedingService: STARTED");
        try{
        Eatable strategy = BehavorStrategyFactory.createEatStrategy(animal, mediator);
            strategy.eat(animal, cell, island, modelThreadPoolManager);
        }catch(Exception e){
            e.printStackTrace();
        }
        Logger.log("FeedingService: FINISHED");
    }

    @Override
    public void handle(Event event) {
        switch (event) {
            case AnimalEatEvent e -> executeEat(e.getEater(), e.getCell(), e.getIsland());
            case null -> throw new RuntimeException("Event cannot be null");
            default -> throw new RuntimeException("Unknown event: " + event.getClass());
        }
    }
}

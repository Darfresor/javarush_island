package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;
import com.javarush.island.ostapenko.util.Logger;

import java.util.UUID;

public class FeedingService implements IEventHandler {
    private final IMediator mediator;
    private final ModelThreadPoolManager modelThreadPoolManager;

    public FeedingService(IMediator mediator, ModelThreadPoolManager modelThreadPoolManager) {
        this.mediator = mediator;
        this.modelThreadPoolManager = modelThreadPoolManager;
    }

    public void executeEat(Island island) {
            for (Cell[] cellVertical : island.getGridCopy()) {
                for (Cell cellHorizontal : cellVertical) {
                    if (cellHorizontal != null) {
                        for (UUID animalId : cellHorizontal.getAnimalIds()) {
                            Animal animal = cellHorizontal.getAnimalById(animalId);
                            if (animal != null && !animal.isBeingEaten()) {
                                Eatable strategy = BehavorFactory.createEatStrategy(animal, mediator);
                                strategy.eat(animal, cellHorizontal, island, modelThreadPoolManager);
                            }
                        }
                    }
                }
            }
    }

    public void executeEat(Animal animal, Cell cell, Island island) {
            Eatable strategy = BehavorFactory.createEatStrategy(animal, mediator);
            strategy.eat(animal, cell, island, modelThreadPoolManager);
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

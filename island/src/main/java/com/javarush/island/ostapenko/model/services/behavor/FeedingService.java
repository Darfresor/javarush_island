package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.util.Logger;

import java.util.concurrent.ExecutorService;

public class FeedingService {
    private final IMediator mediator;
    private final ExecutorService movementServiceThread;

    public FeedingService(IMediator mediator, ExecutorService movementServiceThread) {
        this.mediator = mediator;
        this.movementServiceThread = movementServiceThread;
    }

    public void executeEat(Island island) {
        try {
            for (Cell[] cellVertical : island.getGridCopy()) {
                for (Cell cellHorizontal : cellVertical) {
                    if (cellHorizontal != null) {
                        for (Animal animal : cellHorizontal.getAnimals()) {
                            Eatable strategy = BehavorFactory.createEatStrategy(animal, mediator);
                            strategy.eat(animal, cellHorizontal, island, movementServiceThread);
                        }
                    }
                }
            }
        } finally {
            Logger.flush();
        }
    }
}

package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;

public class FeedingService {
    private final IMediator mediator;

    public FeedingService(IMediator mediator) {
        this.mediator = mediator;
    }

    public void executeEat(Animal animal, Cell cell, Island island) {
            Eatable strategy = BehavorFactory.createEatStrategy(animal, mediator);
            strategy.eat(animal, cell, island);
    }
}

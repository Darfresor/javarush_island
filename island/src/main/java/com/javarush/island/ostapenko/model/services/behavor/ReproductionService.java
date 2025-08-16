package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.AnimalReproducible;
import com.javarush.island.ostapenko.model.behavor.interfaces.PlantReproducible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.util.Logger;

public class ReproductionService {
    private final IMediator mediator;

    public ReproductionService(IMediator mediator) {
        this.mediator = mediator;
    }

    public void executeReproduce(Creature creature, Cell cell, Island island) {
        try {
            switch (creature) {
                case Animal a -> {
                    AnimalReproducible strategy = BehavorFactory.createReproduceStrategy(a, mediator);
                    strategy.reproduce(a, cell, island);
                }
                case Plant p -> {
                    PlantReproducible strategy = BehavorFactory.createReproduceStrategy(p, mediator);
                    strategy.reproduce(p, cell, island);
                }
                case null -> throw new RuntimeException("Creature cannot be null");
                default -> throw new RuntimeException("Unknown creature: " + creature.getClass());
            }
        }finally{
            Logger.flush();
        }

    }
}

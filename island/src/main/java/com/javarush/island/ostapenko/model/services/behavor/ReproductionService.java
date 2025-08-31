package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.AnimalReproducible;
import com.javarush.island.ostapenko.model.behavor.interfaces.PlantReproducible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalCanReproduce;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveForReproduceEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;

import java.util.UUID;

public class ReproductionService implements IEventHandler {
    private final IMediator mediator;
    private final ModelThreadPoolManager modelThreadPoolManager;

    public ReproductionService(IMediator mediator, ModelThreadPoolManager modelThreadPoolManager) {
        this.mediator = mediator;
        this.modelThreadPoolManager = modelThreadPoolManager;
    }

    public void executeReproduce(Island island) {
            for (Cell[] cellVertical : island.getGridCopy()) {
                for (Cell cellHorizontal : cellVertical) {
                    if (cellHorizontal != null) {
                        for (UUID animalId : cellHorizontal.getAnimalIds()) {
                            Animal animal = cellHorizontal.getAnimalById(animalId);
                            if (animal != null) {
                                executeConcreteStrategy(animal, cellHorizontal, island);
                            }
                        }
                        for (UUID plantId : cellHorizontal.getPlantIds()) {
                            Plant plant = cellHorizontal.getPlantById(plantId);
                            if (plant != null ) {
                                executeConcreteStrategy(plant, cellHorizontal, island);
                            }
                        }
                    }
                }
            }
    }

    private void executeConcreteStrategy(Creature creature, Cell cell, Island island){
        switch (creature) {
            case Animal a when !a.isBeingEaten() -> {
                AnimalReproducible strategy = BehavorFactory.createReproduceStrategy(a, mediator);
                strategy.reproduce(a, cell, island, modelThreadPoolManager);
            }
            case Plant p when !p.isBeingEaten()-> {
                PlantReproducible strategy = BehavorFactory.createReproduceStrategy(p, mediator);
                strategy.reproduce(p, cell, island, modelThreadPoolManager);
            }
            case null -> throw new RuntimeException("Creature cannot be null");
            default -> throw new RuntimeException("Unknown creature: " + creature.getClass());
        }
    }

    @Override
    public void handle(Event event) {
        switch (event) {
            case AnimalCanReproduce e -> executeConcreteStrategy(e.getAnimal(), e.getCell(), e.getIsland());
            case null -> throw new RuntimeException("Event cannot be null");
            default -> throw new RuntimeException("Unknown event: " + event.getClass());
        }
    }
}

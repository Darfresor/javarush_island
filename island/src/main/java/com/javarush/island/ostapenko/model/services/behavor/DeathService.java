package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.behavor.interfaces.Starvable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatenEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalStarvationEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.event.PlantEatenEvent;

import java.util.UUID;

public class DeathService implements IEventHandler {
    private final IMediator mediator;
    private final ModelThreadPoolManager modelThreadPoolManager;

    public DeathService(IMediator mediator, ModelThreadPoolManager modelThreadPoolManager) {
        this.mediator = mediator;
        this.modelThreadPoolManager = modelThreadPoolManager;
    }


    public <E extends Creature, T extends Creature> void executeDeathByEating(E predator, T victim, Cell cell) {
            Edible<? super E, ? super T> strategy = (Edible<? super E, ? super T>) BehavorFactory.createBeingEatenStrategy(victim);
            strategy.deathByEating(predator, victim, cell);
    }

    public void executeDeathFromStarvation(Animal animal, Cell cell, Island island) {
            Starvable strategy = BehavorFactory.createStarvableStrategy(animal);
            strategy.deathFromStarvation(animal, cell, island);
    }

    public void executeDeathDueToOldAge(Island island) {
            for (Cell[] cellVertical : island.getGridCopy()) {
                for (Cell cellHorizontal : cellVertical) {
                    if (cellHorizontal != null) {
                        for (UUID animalId : cellHorizontal.getAnimalIds()) {
                            Animal animal = cellHorizontal.getAnimalById(animalId);
                            if (animal != null && !animal.isBeingEaten()) {
                                Aging<Animal> strategy = (Aging<Animal>) BehavorFactory.createAgingStrategy(animal, mediator);
                                strategy.deathDueToOldAge(animal, cellHorizontal, island, modelThreadPoolManager);
                            }
                        }
                        for (UUID plantId : cellHorizontal.getPlantIds()) {
                            Plant plant = cellHorizontal.getPlantById(plantId);
                            if (plant != null && !plant.isBeingEaten()) {
                                Aging<Plant> strategy = (Aging<Plant>) BehavorFactory.createAgingStrategy(plant, mediator);
                                strategy.deathDueToOldAge(plant, cellHorizontal, island, modelThreadPoolManager);
                            }
                        }
                    }
                }
            }
    }

    @Override
    public void handle(Event event) {
        switch (event) {
            case AnimalEatenEvent e -> executeDeathByEating(e.getPredator(), e.getVictim(), e.getCell());
            case AnimalStarvationEvent e -> executeDeathFromStarvation(e.getAnimal(), e.getCell(), e.getIsland());
            case PlantEatenEvent e -> executeDeathByEating(e.getAnimal(), e.getPlant(), e.getCell());
            case null -> throw new RuntimeException("Event cannot be null");
            default -> throw new RuntimeException("Unknown event: " + event.getClass());
        }
    }
}

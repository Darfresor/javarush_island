package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.behavor.interfaces.Starvable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatenEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalStarvationEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.event.PlantEatenEvent;
import com.javarush.island.ostapenko.util.Logger;

public class DeathService implements IEventHandler {
    public <E extends Creature, T extends Creature> void executeDeathByEating(E predator, T victim, Cell cell) {
        try {
            Edible<? super E, ? super T> strategy = (Edible<? super E, ? super T>) BehavorFactory.createBeingEatenStrategy(victim);
            strategy.deathByEating(predator, victim, cell);
        } finally {
            Logger.flush();
        }
    }

    public void executeDeathFromStarvation(Animal animal, Cell cell, Island island) {
        try {
            Starvable strategy = BehavorFactory.createStarvableStrategy(animal);
            strategy.deathFromStarvation(animal, cell, island);
        } finally {
            Logger.flush();
        }
    }

    public  void executeDeathDueToOldAge(Island island) {
        try {
            for (Cell[] cellVertical : island.getGridCopy()) {
                for (Cell cellHorizontal : cellVertical) {
                    if (cellHorizontal != null) {
                        for (Animal animal : cellHorizontal.getAnimals()) {
                            Aging<Animal> strategy = (Aging<Animal>) BehavorFactory.createAgingStrategy(animal);
                            strategy.deathDueToOldAge(animal, cellHorizontal, island);
                        }
                    }
                }
            }
        } finally {
            Logger.flush();
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

package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalDeathByOld;
import com.javarush.island.ostapenko.core.util.Logger;

public class GenericAnimalAgingStrategy<T extends Animal> implements Aging<T> {
    private final IMediator mediator;

    public GenericAnimalAgingStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void deathDueToOldAge(T animal, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        int currentAge = animal.getAgeInDay() + 1;
        int maxAge = animal.getMaxAgeInDay();
        if (currentAge != maxAge) {
            Logger.logDeathService(animal, cell, String.format("%s исполнилось %d дней из %d возможных",
                    animal.getSpeciesName(), currentAge, maxAge));
        } else {
            animal.setAgeInDay(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removeAnimal(animal);
            modelThreadPoolManager.executeDeathTask(() -> mediator.notify(new AnimalDeathByOld(animal, cell, island)));
            Logger.logDeathService(animal, cell, String.format("%s исполнилось %d дней из %d возможных и он умер от старости.",
                    animal.getSpeciesName(), currentAge, maxAge));
        }
    }
}


package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.util.Logger;

public class GenericAnimalAgingStrategy<T extends Animal> implements Aging<T> {
    @Override
    public void deathDueToOldAge(T animal, Cell cell, Island island) {
        int currentAge = animal.getAgeInDay() + 1;
        int maxAge = animal.getMaxAgeInDay();
        if (currentAge != maxAge) {
            Logger.logDeathService(animal, cell, String.format("%s исполнилось %d дней из %d возможных",
                    animal.getSpeciesName(), currentAge, maxAge));
        } else {
            animal.setAgeInDay(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removeAnimal(animal);
            Logger.logDeathService(animal, cell, String.format("%s исполнилось %d дней из %d возможных и он умер от старости.",
                    animal.getSpeciesName(), currentAge, maxAge));
        }
    }
}


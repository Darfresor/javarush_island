package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.util.Logger;

public class WolfAgingStrategy implements Aging<Wolf> {
    @Override
    public void deathDueToOldAge(Wolf wolf, Cell cell, Island island) {
        int currentAge = wolf.getAgeInDay() + 1;
        int maxAge = wolf.getMaxAgeInDay();
        if (currentAge != maxAge) {
            Logger.logDeathService(wolf, cell, String.format("%s исполнилось %d дней из %d возможных",
                    wolf.getSpeciesName(), currentAge, maxAge));
        } else {
            wolf.setAgeInDay(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removeAnimal(wolf);
            Logger.logDeathService(wolf, cell, String.format("%s исполнилось %d дней из %d возможных и он умер от старости.",
                    wolf.getSpeciesName(), currentAge, maxAge));
        }

    }
}

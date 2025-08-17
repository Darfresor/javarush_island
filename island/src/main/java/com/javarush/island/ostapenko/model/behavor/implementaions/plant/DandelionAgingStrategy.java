package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.util.Logger;

public class DandelionAgingStrategy implements Aging<Dandelion> {
    @Override
    public void deathDueToOldAge(Dandelion dandelion, Cell cell, Island island) {
        int currentAge = dandelion.getAgeInDay() + 1;
        int maxAge = dandelion.getMaxAgeInDay();
        if (currentAge != maxAge) {
            Logger.logDeathService(dandelion, cell, String.format("%s исполнилось %d дней из %d возможных",
                    dandelion.getSpeciesName(), currentAge, maxAge));
        } else {
            dandelion.setAgeInDay(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removePlant(dandelion);
            Logger.logDeathService(dandelion, cell, String.format("%s исполнилось %d дней из %d возможных и он умер от старости.",
                    dandelion.getSpeciesName(), currentAge, maxAge));
        }

    }
}

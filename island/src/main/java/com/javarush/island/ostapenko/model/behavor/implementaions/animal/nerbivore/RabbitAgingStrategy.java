package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.util.Logger;

public class RabbitAgingStrategy implements Aging<Rabbit> {
    @Override
    public void deathDueToOldAge(Rabbit rabbit, Cell cell, Island island) {
        int currentAge = rabbit.getAgeInDay() + 1;
        int maxAge = rabbit.getMaxAgeInDay();
        if (currentAge != maxAge) {
            Logger.logDeathService(rabbit, cell, String.format("%s исполнилось %d дней из %d возможных",
                    rabbit.getSpeciesName(), currentAge, maxAge));
        } else {
            rabbit.setAgeInDay(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removeAnimal(rabbit);
            Logger.logDeathService(rabbit, cell, String.format("%s исполнилось %d дней из %d возможных и он умер от старости.",
                    rabbit.getSpeciesName(), currentAge, maxAge));
        }
    }
}

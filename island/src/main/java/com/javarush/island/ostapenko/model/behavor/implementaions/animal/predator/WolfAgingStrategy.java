package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class WolfAgingStrategy implements Aging<Wolf> {
    @Override
    public void deathDueToOldAge(Wolf wolf, Cell cell, Island island) {
        int currentAge = wolf.getAge() + 1;
        int maxAge = wolf.getMaxAge();
        if (currentAge != maxAge) {
            System.out.println(
                    String.format("Волку исполнилось %d лет из %d возможных",
                            wolf.getAge() + 1,maxAge));
        } else {
            wolf.setAge(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removeAnimal(wolf);
            System.out.println(
                    String.format("Волку исполнилось %d лет из %d возможных и он умер от старости",
                            currentAge, maxAge));
        }

    }
}

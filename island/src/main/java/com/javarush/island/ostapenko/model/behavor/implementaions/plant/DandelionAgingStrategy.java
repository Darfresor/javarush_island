package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class DandelionAgingStrategy implements Aging<Dandelion> {
    @Override
    public void deathDueToOldAge(Dandelion dandelion, Cell cell, Island island) {
        int currentAge = dandelion.getAge() + 1;
        int maxAge = dandelion.getMaxAge();
        if (currentAge != maxAge) {
            System.out.println(
                    String.format("одуванчику исполнилось %d лет из %d возможных",
                            dandelion.getAge() + 1,maxAge));
        } else {
            dandelion.setAge(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removePlant(dandelion);
            System.out.println(
                    String.format("одуванчику исполнилось %d лет из %d возможных и он умер от старости",
                            currentAge, maxAge));
        }

    }
}

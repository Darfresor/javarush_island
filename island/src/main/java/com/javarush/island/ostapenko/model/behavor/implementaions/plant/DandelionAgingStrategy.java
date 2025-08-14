package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class DandelionAgingStrategy implements Aging<Dandelion> {
    @Override
    public void deathDueToOldAge(Dandelion dandelion, Cell cell, Island island) {

        System.out.println("Одуванчик завял от старости");
    }
}

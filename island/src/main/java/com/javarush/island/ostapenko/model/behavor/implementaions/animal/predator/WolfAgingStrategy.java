package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;

public class WolfAgingStrategy implements Aging<Wolf> {
    @Override
    public void deathDueToOldAge(Wolf Wolf, Cell cell) {
        System.out.println("Волк умер от старости");
    }
}

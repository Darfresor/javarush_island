package com.javarush.island.ostapenko.model.behavor.implementaions.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class WolfAgingStrategy implements Aging {

    @Override
    public void deathDueToOldAge(Animal animal, Cell cell) {
        System.out.println("Волк умер от старости");
    }
}

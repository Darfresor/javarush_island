package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;

public class DandelionAgingStrategy implements Aging<Dandelion> {
    @Override
    public void deathDueToOldAge(Dandelion dandelion, Cell cell) {

        System.out.println("Растение завяло от старости");
    }
}

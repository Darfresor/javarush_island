package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.model.behavor.interfaces.PlantReproducible;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;

public class DandelionReproduceStrategy implements PlantReproducible {

    @Override
    public void reproduce(Plant plant, Cell cell) {
        System.out.println("Одуванчик размножается");
    }
}

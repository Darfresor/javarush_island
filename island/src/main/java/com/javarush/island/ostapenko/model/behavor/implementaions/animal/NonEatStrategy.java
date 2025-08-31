package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;

public class NonEatStrategy implements Eatable {
    @Override
    public void eat(Animal animal, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        Logger.logFeedingService(animal, cell, String.format("%s не нужно питаться",
                animal.getSpeciesName()));
    }
}

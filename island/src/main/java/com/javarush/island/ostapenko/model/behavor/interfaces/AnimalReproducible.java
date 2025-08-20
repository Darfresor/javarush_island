package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;

public interface AnimalReproducible {
    void reproduce(Animal animal, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager);
}

package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;

public interface PlantReproducible {
    void reproduce(Plant plant, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager);
}

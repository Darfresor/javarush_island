package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public interface PlantReproducible {
    void reproduce(Plant plant, Cell cell, Island island);
}

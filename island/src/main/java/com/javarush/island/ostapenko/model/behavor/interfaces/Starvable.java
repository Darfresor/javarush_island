package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public interface Starvable {
    void deathFromStarvation(Animal animal, Cell cell, Island island);
}

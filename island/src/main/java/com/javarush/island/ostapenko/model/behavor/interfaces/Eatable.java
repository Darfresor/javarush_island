package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.util.concurrent.ExecutorService;

public interface Eatable {
    public void eat(Animal animal, Cell cell, Island island, ExecutorService movementServiceThread);
}

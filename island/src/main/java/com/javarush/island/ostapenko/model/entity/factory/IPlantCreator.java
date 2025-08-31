package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.model.entity.plant.Plant;

@FunctionalInterface
public interface IPlantCreator<T extends Plant> {
    T create();
}

package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.model.entity.animal.Animal;

@FunctionalInterface
public interface IAnimalCreator<T extends Animal> {
    T create();
}

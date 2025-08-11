package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.AnimalReproducible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class WolfReproduceStrategy implements AnimalReproducible {
    @Override
    public void reproduce(Animal animal, Cell cell) {
        System.out.println("Волк размножается");
    }
}

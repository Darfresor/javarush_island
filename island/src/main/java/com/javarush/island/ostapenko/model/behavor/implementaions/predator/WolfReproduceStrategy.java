package com.javarush.island.ostapenko.model.behavor.implementaions.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Breedable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class WolfReproduceStrategy implements Breedable {
    @Override
    public void reproduce(Animal animal, Cell cell) {
        System.out.println("Волк размножается");
    }
}

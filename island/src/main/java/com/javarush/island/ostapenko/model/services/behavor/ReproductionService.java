package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Breedable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class ReproductionService {
    public void executeReproduce(Animal animal, Cell cell) {
            Breedable strategy = BehavorFactory.createReproduceStrategy(animal);
            strategy.reproduce(animal, cell);
    }
}

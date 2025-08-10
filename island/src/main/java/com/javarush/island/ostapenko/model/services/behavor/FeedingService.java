package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class FeedingService {
    public void executeEat(Animal animal, Cell cell) {
            Eatable strategy = BehavorFactory.createEatStrategy(animal);
            strategy.eat(animal, cell);
    }
}

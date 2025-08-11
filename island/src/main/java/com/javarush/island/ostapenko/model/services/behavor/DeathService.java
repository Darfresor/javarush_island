package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.behavor.interfaces.Starvable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.Creature;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;

public class DeathService {
    public void executeDeathByEating(Animal animal, Cell cell) {
            Edible strategy = BehavorFactory.createBeingEatenStrategy(animal);
            strategy.deathByEating(animal, cell);
    }

    public void executeDeathFromStarvation(Animal animal, Cell cell) {
            Starvable strategy = BehavorFactory.createStarvableStrategy(animal);
            strategy.deathFromStarvation(animal, cell);
    }

    public <T extends Creature> void  executeDeathDueToOldAge(T creature, Cell cell) {
            Aging<? super T> strategy = (Aging<? super T>)BehavorFactory.createAgingStrategy(creature);
            strategy.deathDueToOldAge(creature, cell);
    }

}

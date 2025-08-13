package com.javarush.island.ostapenko.model.behavor.implementaions;

import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class NoOpEdibleStrategy implements Edible {
    @Override
    public void deathByEating(Animal predator, Animal victim, Cell cell) {

    }
}

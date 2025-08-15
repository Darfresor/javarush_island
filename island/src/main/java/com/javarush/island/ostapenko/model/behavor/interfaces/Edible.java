package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public interface Edible<E extends Creature, T extends Creature> {
    void deathByEating(E predator, T victim, Cell cell);
}

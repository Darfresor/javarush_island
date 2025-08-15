package com.javarush.island.ostapenko.model.behavor.implementaions;

import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class NoOpEdibleStrategy implements Edible<Creature, Creature> {
    @Override
    public void deathByEating(Creature predator, Creature victim, Cell cell) {

    }
}

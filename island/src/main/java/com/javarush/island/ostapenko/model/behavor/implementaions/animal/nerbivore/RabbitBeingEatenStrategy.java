package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.util.Logger;

public class RabbitBeingEatenStrategy implements Edible<Animal, Animal> {
    @Override
    public void deathByEating(Animal predator, Animal victim, Cell cell) {
        cell.removeAnimal(victim);
        Logger.logDeathService(predator, cell, String.format("%s был съеден %s и умер",
                predator.getSpeciesName(),victim.getSpeciesName()));
    }
}

package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class RabbitBeingEatenStrategy implements Edible {
    @Override
    public void deathByEating(Animal predator, Animal victim, Cell cell) {
        cell.removeAnimal(victim);
        System.out.println(String.format("Кролик был съеден %s и умер",predator.getSpeciesName()));
    }
}

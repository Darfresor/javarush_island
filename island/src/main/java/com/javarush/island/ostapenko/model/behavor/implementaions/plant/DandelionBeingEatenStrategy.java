package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;

public class DandelionBeingEatenStrategy implements Edible<Animal, Plant> {
    @Override
    public void deathByEating(Animal predator, Plant victim, Cell cell) {
        cell.removePlant(victim);
        System.out.println(String.format("Одуванчик был съеден %s и умер",predator.getSpeciesName()));
    }
}

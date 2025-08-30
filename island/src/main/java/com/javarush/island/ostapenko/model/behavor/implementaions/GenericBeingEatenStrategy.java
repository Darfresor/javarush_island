package com.javarush.island.ostapenko.model.behavor.implementaions;

import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.core.util.Logger;

public class GenericBeingEatenStrategy  implements Edible<Animal, Creature> {
    @Override
    public void deathByEating(Animal predator, Creature victim, Cell cell) {
        switch(victim){
            case Animal a -> {
                cell.removeAnimal(a);
                Logger.logDeathService(predator, cell, String.format("%s был съеден %s и умер",
                        a.getSpeciesName(), predator.getSpeciesName()));
            }
            case Plant p -> {
                cell.removePlant(p);
                Logger.logDeathService(predator, cell, String.format("%s был съеден %s и умер",
                        p.getSpeciesName(), predator.getSpeciesName()));
            }
            default -> throw new RuntimeException("Unknown creature: " + victim.getClass());
        }
        

    }
}
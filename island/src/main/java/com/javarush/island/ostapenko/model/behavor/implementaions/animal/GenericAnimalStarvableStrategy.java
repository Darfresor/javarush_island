package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.model.behavor.interfaces.Starvable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.util.Logger;

public class GenericAnimalStarvableStrategy implements Starvable {
    @Override
    public void deathFromStarvation(Animal animal, Cell cell, Island island) {
        Logger.logDeathService(animal, cell, String.format("%s умер от голода.",
                animal.getSpeciesName()));
        Cell originalCell = island.getCell(cell.getX(), cell.getY());
        originalCell.removeAnimal(animal);
    }
}


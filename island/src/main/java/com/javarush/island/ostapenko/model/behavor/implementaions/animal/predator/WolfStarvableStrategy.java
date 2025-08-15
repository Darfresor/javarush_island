package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Starvable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class WolfStarvableStrategy implements Starvable {
    @Override
    public void deathFromStarvation(Animal animal, Cell cell, Island island) {
        System.out.println("Волк умер от голода");
        Cell originalCell = island.getCell(cell.getX(), cell.getY());
        originalCell.removeAnimal(animal);;
    }
}

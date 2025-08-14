package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.AnimalReproducible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class WolfReproduceStrategy implements AnimalReproducible {
    @Override
    public void reproduce(Animal animal, Cell cell, Island island) {
        if(animal.getReprocudedInCurrentTurn()){
            System.out.println(String.format("Волк %s уже участвовал в размножении ранее", animal.hashCode()));
            return;
        }
        for (Animal cellAnimal : cell.getAnimals()) {
            if (cellAnimal.getClass() == animal.getClass()
                    && cellAnimal.getGender() != animal.getGender()
                    && !cellAnimal.getReprocudedInCurrentTurn()
            ) {
                System.out.println(String.format("Волк %s размножается", animal.hashCode()));
                animal.setReprocudedInCurrentTurn(true);
                cellAnimal.setReprocudedInCurrentTurn(true);
                Cell originalCell = island.getCell(cell.getX(),cell.getY());
                originalCell.removeAnimal(animal);
                originalCell.removeAnimal(cellAnimal);
                originalCell.addAnimal(animal);
                originalCell.addAnimal(cellAnimal);
                return;
            }
        }
        System.out.println(String.format("Волк %s не нашел пары для размножения", animal.hashCode()));

    }
}

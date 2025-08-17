package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.model.behavor.interfaces.AnimalReproducible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveForReproduceEvent;
import com.javarush.island.ostapenko.util.Logger;

public class GenericAnimalReproduceStrategy implements AnimalReproducible {
    private final IMediator mediator;

    public GenericAnimalReproduceStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void reproduce(Animal animal, Cell cell, Island island) {
        if(animal.getReprocudedInCurrentTurn()){
            Logger.logReproductionService(animal, cell, String.format("%s уже участвовал в размножении ранее",
                    animal.getSpeciesName()));
            return;
        }
        for (Animal cellAnimal : cell.getAnimals()) {
            if (cellAnimal.getClass() == animal.getClass()
                    && cellAnimal.getGender() != animal.getGender()
                    && !cellAnimal.getReprocudedInCurrentTurn()
            ) {
                Logger.logReproductionService(animal, cell, String.format("%s размножается",
                        animal.getSpeciesName()));
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
        Logger.logReproductionService(animal, cell, String.format("%s не нашел пары для размножения",
                animal.getSpeciesName()));
        mediator.notify(new AnimalMoveForReproduceEvent(animal, cell, island));
    }
}

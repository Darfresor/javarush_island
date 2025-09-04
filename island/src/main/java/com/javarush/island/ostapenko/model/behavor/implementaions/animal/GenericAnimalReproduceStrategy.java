package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.model.behavor.interfaces.AnimalReproducible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.factory.AnimalFactory;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalDeathByOld;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveForReproduceEvent;
import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalReproduce;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class GenericAnimalReproduceStrategy implements AnimalReproducible {
    private final IMediator mediator;

    public GenericAnimalReproduceStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void reproduce(Animal animal, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        if (animal.getReprocudedInCurrentTurn()) {
            Logger.logReproductionService(animal, cell, String.format("%s уже участвовал в размножении ранее",
                    animal.getSpeciesName()));
            return;
        }

        int currentCount = countAnimalInCell(animal, cell);
        if (currentCount >= animal.getMaxNumberOfAnimalInCell()) {
            Logger.logReproductionService(animal, cell, String.format("%s не может размножаться так как его вид достиг предела",
                    animal.getSpeciesName()));
            modelThreadPoolManager.executeMoveTask(() -> mediator.notify(new AnimalMoveForReproduceEvent(animal, cell, island)));
            return;
        }

        for (Animal cellAnimal : cell.getAnimals()) {
            if (cellAnimal == null) continue;

            if (cellAnimal.getClass() == animal.getClass()
                    && cellAnimal.getGender() != animal.getGender()
                    && !cellAnimal.getReprocudedInCurrentTurn()
            ) {
                if (ThreadLocalRandom.current().nextDouble() < 0.5f) {
                    Logger.logReproductionService(animal, cell, String.format("%s размножается",
                            animal.getSpeciesName()));

                    Animal child = AnimalFactory.createAnimal(animal.getClass());
                    child.setReprocudedInCurrentTurn(true);
                    animal.setReprocudedInCurrentTurn(true);
                    cellAnimal.setReprocudedInCurrentTurn(true);

                    cell.addAnimal(child);
                    mediator.notify(new AnimalReproduce(animal, cell, island));

                    return;
                } else {
                    animal.setReprocudedInCurrentTurn(true);
                    cellAnimal.setReprocudedInCurrentTurn(true);
                    Logger.logReproductionService(animal, cell, String.format("%s потерпел неудачу в размножении",
                            animal.getSpeciesName()));
                    return;
                }

            }
        }

        Logger.logReproductionService(animal, cell, String.format("%s не нашел пары для размножения",
                animal.getSpeciesName()));
        modelThreadPoolManager.executeMoveTask(() -> mediator.notify(new AnimalMoveForReproduceEvent(animal, cell, island)));
    }

    private int countAnimalInCell(Animal animal, Cell cell) {
        int countAnimal = 0;
        for (Animal cellAnimal : cell.getAnimals()) {
            if (cellAnimal != null && cellAnimal.getClass() == animal.getClass()) {
                countAnimal++;
                if (countAnimal >= animal.getMaxNumberOfAnimalInCell()) {
                    break;
                }
            }
        }
        return countAnimal;
    }
}

package com.javarush.island.ostapenko.model.services.behavor;


import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.util.Collection;

public class ResetService {
    public void resetAnimalReproducedFlag(Collection<Animal> animals) {
        for (Animal animal : animals) {
            animal.setReprocudedInCurrentTurn(false);

        }
    }

    public void resetAnimalMovementFlag(Collection<Animal> animals) {
        for (Animal animal : animals) {
            animal.setCellsLeftInCurrentTurn(animal.getCellsPerTurnSpeed());
        }
    }

    public void resetAllAnimalFlag(Collection<Animal> animals) {
        resetAnimalReproducedFlag(animals);
        resetAnimalMovementFlag(animals);
    }

    public void resetIslandFlag(Island island) {
        for (Cell[] cellVertical : island.getGridCopy()) {
            for (Cell cellHorizontal : cellVertical) {
                if (cellHorizontal != null) {
                    resetAllAnimalFlag(cellHorizontal.getAnimals());
                }
            }
        }
    }

}

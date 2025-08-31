package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.behavor.interfaces.PlantReproducible;
import com.javarush.island.ostapenko.model.entity.factory.PlantFactory;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.PlantReproduce;

import java.util.UUID;

public class GenericPlantReproduceStrategy implements PlantReproducible {
    private final IMediator mediator;

    public GenericPlantReproduceStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void reproduce(Plant plant, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        for (UUID plantId : cell.getPlantIds()) {
            int countMaxPlantInCell = countPlantInCell(plant, cell);
            Plant cellPlant = cell.getPlantById(plantId);
                if (countMaxPlantInCell < plant.getMaxNumberOfPlantInCell()) {
                    Logger.logReproductionService(plant, cell, String.format("%s размножается",
                            plant.getSpeciesName()));
                    Plant child = PlantFactory.createPlant(plant.getClass());
                    child.setReprocudedInCurrentTurn(true);
                    mediator.notify(new PlantReproduce(plant, cell, island));
                    plant.setReprocudedInCurrentTurn(true);
                    Cell originalCell = island.getCell(cell.getX(), cell.getY());
                    originalCell.removePlant(plant);
                    originalCell.addPlant(plant);
                    originalCell.addPlant(child);
                } else {
                    Logger.logReproductionService(plant, cell, String.format("%s не может размножаться так как его вид достиг предела",
                            plant.getSpeciesName()));
                }
                return;
        }
    }

    private int countPlantInCell(Plant plant, Cell cell) {
        int countPlant = 0;
        for (UUID plantId : cell.getPlantIds()) {
            Plant cellPlant = cell.getPlantById(plantId);
            if (cellPlant.getClass() == plant.getClass()
            ) {
                countPlant++;
            }
        }
        return countPlant;
    }
}

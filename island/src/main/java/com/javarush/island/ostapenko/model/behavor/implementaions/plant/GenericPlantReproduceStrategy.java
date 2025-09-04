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

import java.util.List;
import java.util.UUID;

public class GenericPlantReproduceStrategy implements PlantReproducible {
    private final IMediator mediator;

    public GenericPlantReproduceStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void reproduce(Plant plant, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        if (plant.getReprocudedInCurrentTurn()) {
            Logger.logReproductionService(plant, cell,
                    String.format("%s уже размножался в этом ходу", plant.getSpeciesName()));
            return;
        }

        boolean reproductionSuccessful = attemptPlantReproduction(plant, cell);

        if (reproductionSuccessful) {
            mediator.notify(new PlantReproduce(plant, cell, island));
        }
    }

    private boolean attemptPlantReproduction(Plant plant, Cell cell) {
        synchronized (plant) {
            if (plant.getReprocudedInCurrentTurn()) {
                return false; // Уже размножались в другом потоке
            }


            List<UUID> plantIds = List.of(cell.getPlantIds());
            int currentCount = countPlantsOfType(plant.getClass(), plantIds, cell);

            if (currentCount >= plant.getMaxNumberOfPlantInCell()) {
                Logger.logReproductionService(plant, cell,
                        String.format("%s не может размножаться. Лимит: %d, текущее: %d",
                                plant.getSpeciesName(), plant.getMaxNumberOfPlantInCell(), currentCount));
                return false;
            }

            return performPlantReproduction(plant, cell);
        }
    }

    private int countPlantsOfType(Class<? extends Plant> plantType, List<UUID> plantIds, Cell cell) {
        int count = 0;
        for (UUID plantId : plantIds) {
            Plant currentPlant = cell.getPlantById(plantId);
            if (currentPlant != null && currentPlant.getClass() == plantType) {
                count++;
            }
        }
        return count;
    }

    private boolean performPlantReproduction(Plant plant, Cell cell) {
        Plant child = PlantFactory.createPlant(plant.getClass());
        child.setReprocudedInCurrentTurn(true);
        plant.setReprocudedInCurrentTurn(true);

        boolean added = cell.addPlantAtomically(plant, child);

        if (added) {
            Logger.logReproductionService(plant, cell,
                    String.format("%s успешно размножился", plant.getSpeciesName()));
            return true;
        } else {
            plant.setReprocudedInCurrentTurn(false);
            Logger.logReproductionService(plant, cell,
                    String.format("%s не смог размножиться (конфликт)", plant.getSpeciesName()));
            return false;
        }
    }


}

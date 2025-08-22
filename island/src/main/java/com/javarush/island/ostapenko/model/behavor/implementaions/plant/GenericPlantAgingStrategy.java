package com.javarush.island.ostapenko.model.behavor.implementaions.plant;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalDeathByOld;
import com.javarush.island.ostapenko.model.services.mediator.event.PlantDeathByOld;
import com.javarush.island.ostapenko.util.Logger;

public class GenericPlantAgingStrategy<T extends Plant> implements Aging<T> {
    private final IMediator mediator;

    public GenericPlantAgingStrategy(IMediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public void deathDueToOldAge(T plant, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        int currentAge = plant.getAgeInDay() + 1;
        int maxAge = plant.getMaxAgeInDay();
        if (currentAge != maxAge) {
            Logger.logDeathService(plant, cell, String.format("%s исполнилось %d дней из %d возможных",
                    plant.getSpeciesName(), currentAge, maxAge));
        } else {
            plant.setAgeInDay(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removePlant(plant);
            modelThreadPoolManager.executeDeathTask(() -> mediator.notify(new PlantDeathByOld(plant, cell, island)));
            Logger.logDeathService(plant, cell, String.format("%s исполнилось %d дней из %d возможных и он завял.",
                    plant.getSpeciesName(), currentAge, maxAge));
        }
    }
}

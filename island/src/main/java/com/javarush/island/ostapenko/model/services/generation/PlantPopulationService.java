package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.constants.GenerateCreatureType;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.services.generation.factory.AnimalFactory;
import com.javarush.island.ostapenko.model.services.generation.factory.PlantFactory;

public class PlantPopulationService {
    private final Island island;

    public PlantPopulationService(Island island) {
        this.island = island;
    }
    public void generate(GenerateCreatureType generateCreatureType){
        switch (generateCreatureType) {
            case FOR_EXAMPLE -> generateSimpleExample();
            case DEFAULT -> generateDefaultAnimal();
        }
        Logger.log("Генерация растений завершена");
    }

    private void generateSimpleExample() {
        Cell cell = island.getGridCopy()[0][1];
        Plant dandelion1 = PlantFactory.createPlant(2*365+364);
        Plant dandelion2 = PlantFactory.createPlant();
        cell.addPlant(dandelion1);
        cell.addPlant(dandelion2);
        island.setCell(cell);
    }

    private void generateDefaultAnimal() {
        Cell cell = island.getGridCopy()[0][1];
        Plant dandelion2 = PlantFactory.createPlant();
        cell.addPlant(dandelion2);
        island.setCell(cell);
    }
}

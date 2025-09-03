package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.constants.GenerateCreatureType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.factory.DefaultBiomFactory;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.entity.factory.PlantFactory;

import java.util.List;

public class PlantPopulationService {
    private final Island island;

    public PlantPopulationService(Island island) {
        this.island = island;
    }
    public void generate(GenerateCreatureType generateCreatureType){
        switch (generateCreatureType) {
            case FOR_EXAMPLE_DUCK -> generateSimpleExampleDuck();
            case FOR_EXAMPLE_WOLF -> generateSimpleExampleWolf();
            case DEFAULT -> generateDefaultAnimal();
        }
        Logger.log("Генерация растений завершена");
    }

    private void generateSimpleExampleDuck() {
        Cell cell = island.getGridCopy()[0][0];
        Plant dandelion1 = PlantFactory.createDandelion(2*365+364);
        Plant dandelion2 = PlantFactory.createDandelion();
        Plant dandelion3 = PlantFactory.createDandelion();
        Plant dandelion4 = PlantFactory.createDandelion();
        cell.addPlant(dandelion1);
        cell.addPlant(dandelion2);
        cell.addPlant(dandelion3);
        cell.addPlant(dandelion4);
        island.setCell(cell);
    }
    private void generateSimpleExampleWolf() {
        Cell cell = island.getGridCopy()[0][1];
        Plant dandelion1 = PlantFactory.createDandelion();
        Plant dandelion2 = PlantFactory.createDandelion();
        Plant dandelion3 = PlantFactory.createDandelion();
        Plant dandelion4 = PlantFactory.createDandelion();
        Plant dandelion5 = PlantFactory.createDandelion();
        Plant dandelion6 = PlantFactory.createDandelion();
        Plant dandelion7 = PlantFactory.createDandelion();
        Plant dandelion8 = PlantFactory.createDandelion();
        Plant dandelion9 = PlantFactory.createDandelion();
        Plant dandelion10 = PlantFactory.createDandelion();
        Plant dandelion11 = PlantFactory.createDandelion();
        Plant dandelion12 = PlantFactory.createDandelion();
        Plant dandelion13 = PlantFactory.createDandelion();
        Plant dandelion14 = PlantFactory.createDandelion();
        cell.addPlant(dandelion1);
        cell.addPlant(dandelion2);
        cell.addPlant(dandelion3);
        cell.addPlant(dandelion4);
        cell.addPlant(dandelion5);
        cell.addPlant(dandelion6);
        cell.addPlant(dandelion7);
        cell.addPlant(dandelion8);
        cell.addPlant(dandelion9);
        cell.addPlant(dandelion10);
        cell.addPlant(dandelion11);
        cell.addPlant(dandelion12);
        cell.addPlant(dandelion13);
        cell.addPlant(dandelion14);
        island.setCell(cell);
    }

    private void generateDefaultAnimal() {
        Cell cell = island.getGridCopy()[0][1];
        Plant dandelion2 = PlantFactory.createDandelion();

        List<Plant> plants = DefaultBiomFactory.createAllPlants();
        cell.addPlants(plants);
        island.setCell(cell);


    }
}

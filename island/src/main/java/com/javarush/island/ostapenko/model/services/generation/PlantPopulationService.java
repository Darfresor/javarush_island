package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.util.List;

public class PlantPopulationService {
    private final Island island;

    public PlantPopulationService(Island island) {
        this.island = island;
    }
    public void generate(){

        Cell cell = island.getGridCopy()[0][1];
        Plant dandelion1 = new Dandelion("Одуванчик",2*365+364,3*365,1f,1f,200);
        Plant dandelion2 = new Dandelion("Одуванчик",1*365,3*365,1f,1f,200);
        cell.addPlant(dandelion1);
        cell.addPlant(dandelion2);
        island.setCell(cell);

        for (int i = 0; i < island.getGridCopy().length; i++) {
            for (int j = 0; j < island.getGridCopy()[0].length; j++) {
                if(island.getGridCopy()[i][j]!=null){
                    List<Plant> listPlant = island.getGridCopy()[i][j].getPlants();
                    System.out.println(String.format("Клетка [%d:%d] содержит растения: %s",i, j
                            ,listPlant
                    ));
                }else{
                    System.out.println(String.format("Клетка [%d:%d] без растений",i, j));
                }

            }
        }
    }
}

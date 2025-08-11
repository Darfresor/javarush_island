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

        Cell cell = island.getCells()[0][0];
        Plant dandelion = new Dandelion();
        cell.addPlant(dandelion);
        island.setCell(cell);

        for (int i = 0; i < island.getCells().length; i++) {
            for (int j = 0; j < island.getCells()[0].length; j++) {
                if(island.getCells()[i][j]!=null){
                    List<Plant> listPlant = island.getCells()[i][j].getPlants();
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

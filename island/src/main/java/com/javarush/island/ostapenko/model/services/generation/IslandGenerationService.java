package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.constants.GenerateCreatureType;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.core.util.Logger;

public class IslandGenerationService {
    private final int numOfCellX;
    private final int getNumOfCellY;

    public IslandGenerationService(int numOfCellX, int getNumOfCellY) {
        this.numOfCellX = numOfCellX;
        this.getNumOfCellY = getNumOfCellY;
    }

    public Island generate(GenerateCreatureType generateCreatureType){
        Cell[][] grid = new Cell[numOfCellX][getNumOfCellY];
        Island island = new Island(grid);
        Logger.log("Генерация пустого острова завершена");
        new AnimalPopulationService(island).generate(generateCreatureType);
        new PlantPopulationService(island).generate(generateCreatureType);

        return island;
    }
}

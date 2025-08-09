package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class IslandGenerationService {
    public IslandGenerationService() {
    }

    public Island generate(){
        Cell[][] grid = new Cell[2][2];
        Island island = new Island(grid);
        System.out.println("Генерация пустого острова завершена");
        return island;
    }
}

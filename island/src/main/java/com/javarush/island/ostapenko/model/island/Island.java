package com.javarush.island.ostapenko.model.island;


import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Island {
    private final Map<String, Cell> cellsMap = new ConcurrentHashMap<>();
    private final int width;
    private final int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = new Cell(x, y);
                cellsMap.put(getKey(x, y), cell);
            }
        }
    }

    private String getKey(int x, int y) {
        return x + "," + y;
    }




    public Cell getCell(int x, int y) {
        return cellsMap.get(getKey(x,y));

    }

    public void setCell(Cell cell) {
        cellsMap.put(getKey(cell.getX(),cell.getY()),cell);
    }


    public Cell[][] getGridCopy() {
       Cell[][] grid = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = cellsMap.get(getKey(x,y));
            }
        }
       return grid;
    }


    public Map<String, Long> getAnimalCountBySpecies() {
        Map<String, Long> countBySpecies = new ConcurrentHashMap<>();
        cellsMap.forEach((key,cell)->{
            for (Animal animal : cell.getAnimals()) {
                String speciesName = animal.getClass().getSimpleName();
                countBySpecies.merge(speciesName, 1L, Long::sum);
            }
        });
        return countBySpecies;
    }



    public Map<String, Long> getPlantCountBySpecies() {
        Map<String, Long> countBySpecies = new ConcurrentHashMap<>();
        cellsMap.forEach((key,cell)->{
            for (Plant plant : cell.getPlants()) {
                String speciesName = plant.getClass().getSimpleName();
                countBySpecies.merge(speciesName, 1L, Long::sum);
            }
        });
        return countBySpecies;
    }

    public Collection<Cell>  getAllCells(){
        return cellsMap.values();
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

}

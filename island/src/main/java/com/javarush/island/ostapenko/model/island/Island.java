package com.javarush.island.ostapenko.model.island;



import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Island {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Cell[][] grid;

    public Island(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell getCell(int x, int y) {
        lock.readLock().lock();
        try {
            return grid[x][y];
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setCell(Cell cell) {
        lock.writeLock().lock();
        try {
            int x = cell.getX();
            int y = cell.getY();
            grid[x][y] = cell;
        } finally {
            lock.writeLock().unlock();
        }

    }

    public Cell[][] getGridCopy() {
        lock.readLock().lock();
        try {
            return Arrays.copyOf(grid, grid.length);
        } finally {
            lock.readLock().unlock();
        }
    }

    public Map<String, Long> getAnimalCountBySpecies() {
        Map<String, Long> countBySpecies = new HashMap<>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                Cell cell = grid[x][y];
                if (cell != null) {
                    for(Animal animal: cell.getAnimals()){
                        String speciesName = animal.getClass().getSimpleName();
                        countBySpecies.merge(speciesName,1L, Long::sum);
                    }
                }
            }
        }
        return countBySpecies;
    }
    public Map<String, Long> getPlantCountBySpecies() {
        Map<String, Long> countBySpecies = new HashMap<>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                Cell cell = grid[x][y];
                if (cell != null) {
                    for(Plant plant: cell.getPlants()){
                        String speciesName = plant.getClass().getSimpleName();
                        countBySpecies.merge(speciesName,1L, Long::sum);
                    }
                }
            }
        }
        return countBySpecies;
    }

}

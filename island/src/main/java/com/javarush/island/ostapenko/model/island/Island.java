package com.javarush.island.ostapenko.model.island;

import java.util.Arrays;
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
}

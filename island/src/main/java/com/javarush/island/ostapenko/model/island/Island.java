package com.javarush.island.ostapenko.model.island;

public class Island {
    private final Cell[][] grid;

    public Island(Cell[][] grid) {
        this.grid = grid;
    }

    public Cell[][] getCells(){
        return grid;
    }

    public void setCell(Cell cell){
        int x = cell.getX();
        int y = cell.getY();
        grid[x][y] = cell;
    }
}

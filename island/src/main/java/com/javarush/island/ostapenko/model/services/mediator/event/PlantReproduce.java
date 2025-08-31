package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class PlantReproduce implements Event{
    private final Plant plant;
    private final Cell cell;
    private final Island island;

    public PlantReproduce(Plant plant, Cell cell, Island island) {
        this.plant = plant;
        this.cell = cell;
        this.island = island;
    }


    @Override
    public EventType getType() {
        return EventType.PLANT_REPRODUCE;
    }

    public Plant getPlant() {
        return plant;
    }

    public Island getIsland() {
        return island;
    }

    public Cell getCell() {
        return cell;
    }
}

package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class PlantDeathByOld implements Event{
    private final Plant plant;
    private final Cell cell;
    private final Island island;

    public PlantDeathByOld(Plant plant, Cell cell, Island island) {
        this.plant = plant;
        this.cell = cell;
        this.island = island;
    }

    @Override
    public EventType getType() {
        return EventType.PLANT_DEATH_BY_OLD;
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

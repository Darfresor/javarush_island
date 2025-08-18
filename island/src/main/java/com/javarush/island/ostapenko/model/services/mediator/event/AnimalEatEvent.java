package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class AnimalEatEvent implements Event{
    private final Animal eater;
    private final Cell cell;
    private final Island island;

    public AnimalEatEvent(Animal predator, Cell cell, Island island) {
        this.eater = predator;
        this.cell = cell;
        this.island = island;
    }


    @Override
    public EventType getType() {
        return EventType.ANIMAL_EAT;
    }

    public Animal getEater() {
        return eater;
    }

    public Island getIsland() {
        return island;
    }

    public Cell getCell() {
        return cell;
    }
}

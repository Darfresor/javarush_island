package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class AnimalCanReproduce implements Event{
    private final Animal animal;
    private final Cell cell;
    private final Island island;

    public AnimalCanReproduce(Animal animal, Cell cell, Island island) {
        this.animal = animal;
        this.cell = cell;
        this.island = island;
    }


    @Override
    public EventType getType() {
        return EventType.ANIMAL_CAN_REPRODUCE;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Island getIsland() {
        return island;
    }

    public Cell getCell() {
        return cell;
    }
}

package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import static com.javarush.island.ostapenko.constants.EventType.ANIMAL_STARVATION;

public class AnimalStarvationEvent implements Event{
    private final Animal animal;
    private final Cell cell;
    private final Island island;

    public AnimalStarvationEvent(Animal animal, Cell cell, Island island) {
        this.animal = animal;
        this.cell = cell;
        this.island = island;
    }

    @Override
    public EventType getType() {
        return ANIMAL_STARVATION;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Cell getCell() {
        return cell;
    }

    public Island getIsland() {
        return island;
    }
}

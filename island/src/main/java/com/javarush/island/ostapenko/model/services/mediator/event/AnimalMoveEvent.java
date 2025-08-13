package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import static com.javarush.island.ostapenko.constants.EventType.ANIMAL_MOVE;


public class AnimalMoveEvent implements Event{
    private final Animal animal;
    private final Cell currentCell;
    private final Island island;

    public AnimalMoveEvent(Animal animal, Cell currentCell, Island island) {
        this.animal = animal;
        this.currentCell = currentCell;
        this.island = island;
    }

    @Override
    public EventType getType() {
        return ANIMAL_MOVE;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Island getIsland() {
        return island;
    }
}

package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class AnimalEatenEvent implements Event{
    private final Animal predator;
    private final Animal victim;
    private final Cell cell;

    public AnimalEatenEvent(Animal predator, Animal victim, Cell cell) {
        this.predator = predator;
        this.victim = victim;
        this.cell = cell;
    }

    @Override
    public EventType getType() {
        return EventType.ANIMAL_EATEN;
    }

    public Animal getPredator() {
        return predator;
    }

    public Animal getVictim() {
        return victim;
    }

    public Cell getCell() {
        return cell;
    }
}

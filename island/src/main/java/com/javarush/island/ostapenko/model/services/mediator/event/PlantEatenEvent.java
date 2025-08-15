package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;

public class PlantEatenEvent implements Event{
    private final Animal animal;
    private final Plant plant;
    private final Cell cell;

    public PlantEatenEvent(Animal animal, Plant plant, Cell cell) {
        this.animal = animal;
        this.plant = plant;
        this.cell = cell;
    }


    @Override
    public EventType getType() {
        return EventType.PLANT_EATEN;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Plant getPlant() {
        return plant;
    }

    public Cell getCell() {
        return cell;
    }
}

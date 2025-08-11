package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class RabbitAgingStrategy implements Aging<Animal> {
    @Override
    public void deathDueToOldAge(Animal animal, Cell cell) {
        System.out.println("Кролик умер от старости");
    }
}

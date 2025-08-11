package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Starvable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class RabbitStarvableStrategy implements Starvable {
    @Override
    public void deathFromStarvation(Animal animal, Cell cell) {
        System.out.println("Кролик умер от голода");
    }
}

package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Starvable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class WolfStarvableStrategy implements Starvable {
    @Override
    public void deathFromStarvation(Animal animal, Cell cell) {
        System.out.println("Волк умер от голода");
    }
}

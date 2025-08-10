package com.javarush.island.ostapenko.model.behavor.implementaions.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Edible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class RabbitBeingEatenStrategy implements Edible {
    @Override
    public void deathByEating(Animal animal, Cell cell) {
        System.out.println("Кролик был съеден и умер");
    }
}

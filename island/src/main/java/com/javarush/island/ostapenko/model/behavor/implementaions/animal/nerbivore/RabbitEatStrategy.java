package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class RabbitEatStrategy implements Eatable {
    @Override
    public void eat(Animal animal, Cell cell, Island island) {
        System.out.println("Кролик ест");
    }
}

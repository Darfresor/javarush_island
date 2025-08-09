package com.javarush.island.ostapenko.model.behavor.implementaions.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class RabbitEatStrategy implements Eatable {
    @Override
    public void eat(Animal animal, Cell cell) {
        System.out.println("Кролик ест");
    }
}

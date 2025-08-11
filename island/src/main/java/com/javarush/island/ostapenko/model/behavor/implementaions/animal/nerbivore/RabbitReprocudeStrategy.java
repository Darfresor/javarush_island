package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.AnimalReproducible;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class RabbitReprocudeStrategy implements AnimalReproducible {
    @Override
    public void reproduce(Animal animal, Cell cell) {
        System.out.println("Кролик размножается");
    }
}

package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class RabbitMoveStrategy implements Moveable {
    @Override
    public void move(Animal animal, Cell currentCell, Island island) {
        System.out.println("Кролик двигается");
    }
}

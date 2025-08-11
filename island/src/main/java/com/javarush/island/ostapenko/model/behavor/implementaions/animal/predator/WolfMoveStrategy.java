package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Island;

public class WolfMoveStrategy implements Moveable {
    @Override
    public void move(Animal animal, Island island) {
        System.out.println("Волк двигается");
    }
}

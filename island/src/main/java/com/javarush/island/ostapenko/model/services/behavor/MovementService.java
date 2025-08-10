package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Island;

public class MovementService {
    public void executeMove(Animal animal, Island island) {
            Moveable strategy = BehavorFactory.createMoveStrategy(animal);
            strategy.move(animal, island);

    }
}

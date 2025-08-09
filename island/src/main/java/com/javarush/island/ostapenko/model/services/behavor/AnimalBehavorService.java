package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Breedable;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class AnimalBehavorService {
    public AnimalBehavorService() {
    }

    public void executeMove(Animal animal, Island island) {
        Moveable strategy = BehavorFactory.createMoveStrategy(animal);
        strategy.move(animal, island);
    }

    public void executeEat(Animal animal, Cell cell) {
        Eatable strategy = BehavorFactory.createEatStrategy(animal);
        strategy.eat(animal, cell);
    }

    public void executeReproduce(Animal animal, Cell cell) {
        Breedable strategy = BehavorFactory.createReproduceStrategy(animal);
        strategy.reproduce(animal, cell);
    }
}

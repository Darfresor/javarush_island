package com.javarush.island.ostapenko.model.behavor.implementaions.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;

public class WolfEatStrategy implements Eatable {
    @Override
    public void eat(Animal animal, Cell cell) {
        System.out.println("Волк ест");
    }
}

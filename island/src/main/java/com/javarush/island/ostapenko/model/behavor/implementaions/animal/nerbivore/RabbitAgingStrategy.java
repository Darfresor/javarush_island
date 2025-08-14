package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Aging;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public class RabbitAgingStrategy implements Aging<Rabbit> {
    @Override
    public void deathDueToOldAge(Rabbit rabbit, Cell cell, Island island) {
        int currentAge = rabbit.getAge() + 1;
        int maxAge = rabbit.getMaxAge();
        if (currentAge != maxAge) {
            System.out.println(
                    String.format("Кролику исполнилось %d лет из %d возможных",
                            rabbit.getAge() + 1,maxAge));
        } else {
            rabbit.setAge(currentAge);
            Cell originalCell = island.getCell(cell.getX(), cell.getY());
            originalCell.removeAnimal(rabbit);
            System.out.println(
                    String.format("Кролику исполнилось %d лет из %d возможных и он умер от старости",
                            currentAge, maxAge));
        }
    }
}

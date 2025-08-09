package com.javarush.island.ostapenko.tests;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.AnimalBehavorService;

public class IslandTest {
    public static void main(String[] args) {

        Cell[][] grid = new Cell[10][10];
        Cell cell = new Cell(0,0);
        grid[0][0] = cell;

        Wolf wolf = new Wolf();
        Rabbit rabbit = new Rabbit();
        cell.addAnimal(wolf);
        cell.addAnimal(rabbit);

        Island island = new Island(grid);
        System.out.println(island);

        AnimalBehavorService service =  new AnimalBehavorService();


        for (Animal animal : cell.getAnimals()) {
            service.executeMove(animal, island);
            service.executeEat(animal, cell);
            service.executeReproduce(animal, cell);
        }




    }
}

package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.util.ArrayList;
import java.util.List;

public class AnimalPopulationService {
    private final Island island;

    public AnimalPopulationService(Island island) {
        this.island = island;
    }

    public Island generate() {
        Cell cell = new Cell(0, 0);
        Wolf wolf = new Wolf();
        Rabbit rabbit = new Rabbit();
        cell.addAnimal(wolf);
        cell.addAnimal(rabbit);
        island.setCell(cell);

        for (int i = 0; i < island.getCells().length; i++) {
            for (int j = 0; j < island.getCells()[0].length; j++) {
                if(island.getCells()[i][j]!=null){
                    List<Animal> listAnimal = island.getCells()[i][j].getAnimals();
                    System.out.println(String.format("Клетка [%d:%d] содержит животных: %s",i, j
                    ,listAnimal
                    ));
                }else{
                    System.out.println(String.format("Клетка [%d:%d] пустая",i, j));
                }

            }
        }

        return island;
    }
}

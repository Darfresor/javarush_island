package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.util.ArrayList;
import java.util.List;

import static com.javarush.island.ostapenko.constants.Gender.MALE;

public class AnimalPopulationService {
    private final Island island;

    public AnimalPopulationService(Island island) {
        this.island = island;
    }



    public void generate() {
        Cell cell = new Cell(0, 0);
        Wolf wolf = new Wolf("Волк",3,10,MALE,50f,50f,3,8,0.5f);
        Rabbit rabbit = new Rabbit("Кролик",3,10,MALE,2f,2f,2,0.45f, 0.5f);
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
                    System.out.println(String.format("Клетка [%d:%d] без животных",i, j));
                }

            }
        }
    }
}

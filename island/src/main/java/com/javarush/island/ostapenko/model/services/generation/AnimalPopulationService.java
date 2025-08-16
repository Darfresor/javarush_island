package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.util.ArrayList;
import java.util.List;

import static com.javarush.island.ostapenko.constants.Gender.FEMALE;
import static com.javarush.island.ostapenko.constants.Gender.MALE;

public class AnimalPopulationService {
    private final Island island;

    public AnimalPopulationService(Island island) {
        this.island = island;
    }



    public void generate() {
        Cell cell = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);
        Wolf wolf1 = new Wolf("Волк",9*365+364,10*365,MALE,50f,50f,3,8,0.5f);
        Wolf wolf2 = new Wolf("Волк",3*365,10*365,MALE,50f,50f,3,8,0.5f);
        Wolf wolf3 = new Wolf("Волк",3*365,10*365,FEMALE,50f,50f,3,8,0.5f);
        Rabbit rabbit1 = new Rabbit("Кролик",8*365+364,10*365,MALE,2f,2f,2,0.45f, 0.5f);
        Rabbit rabbit2 = new Rabbit("Кролик",3*365,10*365,FEMALE,2f,2f,2,0.45f, 0.5f);
        //cell.addAnimal(wolf1);
        cell.addAnimal(wolf2);
        cell.addAnimal(wolf3);
        //cell2.addAnimal(rabbit1);
        //cell2.addAnimal(rabbit2);
        island.setCell(cell); //0.0
        island.setCell(cell2);//0.1
        island.setCell(new Cell(1,0));//1.0
        island.setCell(new Cell(1,1));//1.1

        for (int i = 0; i < island.getGridCopy().length; i++) {
            for (int j = 0; j < island.getGridCopy()[0].length; j++) {
                if(island.getGridCopy()[i][j]!=null){
                    List<Animal> listAnimal = island.getGridCopy()[i][j].getAnimals();
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

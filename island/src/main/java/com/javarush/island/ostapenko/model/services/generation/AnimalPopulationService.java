package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.constants.GenerateCreatureType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Duck;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.entity.factory.AnimalFactory;

public class AnimalPopulationService {
    private final Island island;

    public AnimalPopulationService(Island island) {
        this.island = island;
    }


    public void generate(GenerateCreatureType generateCreatureType) {
        switch (generateCreatureType) {
            case FOR_EXAMPLE -> generateSimpleExample();
            case DEFAULT -> generateDefaultAnimal();
        }
        Logger.log("Генерация животных завершена");
    }

    private void generateSimpleExample() {
        Cell cell = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);
        Cell cell3 = new Cell(1, 0);
        Cell cell4 = new Cell(1, 1);
       // Wolf wolf1 = AnimalFactory.createWolf(364 + 9 * 365);
       // Wolf wolf2 = AnimalFactory.createWolf(Gender.MALE);
        //Wolf wolf3 = AnimalFactory.createWolf(Gender.FEMALE);

        //Rabbit rabbit1 = AnimalFactory.createRabbit(364 + 9 * 365);
        //Rabbit rabbit2 = AnimalFactory.createRabbit(Gender.MALE);
        Duck duck1 = AnimalFactory.createDuck();
        //Duck duck2 = AnimalFactory.createDuck();
        //cell.addAnimal(wolf1);
        //cell.addAnimal(wolf2);
        //cell.addAnimal(rabbit1);
        //cell.addAnimal(rabbit2);
        //cell.addAnimal(wolf3);
        cell.addAnimal(duck1);
        //cell2.addAnimal(duck2);
        /*
        Wolf wolf10 = AnimalFactory.createWolf(Gender.MALE);
        Wolf wolf20 = AnimalFactory.createWolf(Gender.MALE);
        Wolf wolf30 = AnimalFactory.createWolf(Gender.MALE);
        Wolf wolf40 = AnimalFactory.createWolf(Gender.MALE);
        Wolf wolf50 = AnimalFactory.createWolf(Gender.MALE);
        Wolf wolf60 = AnimalFactory.createWolf(Gender.MALE);
        cell2.addAnimal(wolf10);
        cell2.addAnimal(wolf20);
        cell3.addAnimal(wolf30);
        cell3.addAnimal(wolf40);
        cell4.addAnimal(wolf40);
        cell4.addAnimal(wolf50);
        */


        island.setCell(cell); //0.0
        island.setCell(cell2);//0.1
        island.setCell(cell3);//1.0
        island.setCell(cell4);//1.1
    }

    private void generateDefaultAnimal() {
        Cell cell = new Cell(0, 0);
        Wolf wolf1 = AnimalFactory.createWolf();
        cell.addAnimal(wolf1);
        island.setCell(cell); //0.0
        island.setCell(new Cell(0, 1));//1.0
        island.setCell(new Cell(1, 0));//1.0
        island.setCell(new Cell(1, 1));//1.1
    }
}

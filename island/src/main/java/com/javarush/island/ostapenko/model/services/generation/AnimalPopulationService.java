package com.javarush.island.ostapenko.model.services.generation;

import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.constants.GenerateCreatureType;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Duck;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.insetcs.Caterpillar;
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
            case FOR_EXAMPLE_DUCK -> generateSimpleExampleDuck();
            case FOR_EXAMPLE_WOLF -> generateSimpleExampleWolf();
            case DEFAULT -> generateDefaultAnimal();
        }
        Logger.log("Генерация животных завершена");
    }



    private void generateSimpleExampleDuck() {
        Cell cell = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);
        Cell cell3 = new Cell(1, 0);
        Cell cell4 = new Cell(1, 1);
        Duck duck1 = AnimalFactory.createDuck();
        Caterpillar caterpillar1 = AnimalFactory.createCaterpillar();
        Caterpillar caterpillar2 = AnimalFactory.createCaterpillar();
        cell.addAnimal(duck1);
        cell.addAnimal(caterpillar1);
        cell.addAnimal(caterpillar2);
        island.setCell(cell);
        island.setCell(cell2);
        island.setCell(cell3);
        island.setCell(cell4);
    }

    private void generateSimpleExampleWolf() {
        Cell cell = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);
        Cell cell3 = new Cell(1, 0);
        Cell cell4 = new Cell(1, 1);
        Wolf wolf1 = AnimalFactory.createWolf(364 + 9 * 365);
        Wolf wolf2 = AnimalFactory.createWolf(Gender.MALE);
        Wolf wolf3 = AnimalFactory.createWolf(Gender.FEMALE);

        Rabbit rabbit1 = AnimalFactory.createRabbit(Gender.MALE);
        Rabbit rabbit2 = AnimalFactory.createRabbit(Gender.FEMALE);
        Rabbit rabbit3 = AnimalFactory.createRabbit(Gender.MALE);
        Rabbit rabbit4 = AnimalFactory.createRabbit(Gender.FEMALE);
        Rabbit rabbit5 = AnimalFactory.createRabbit(Gender.FEMALE);
        Rabbit rabbit6 = AnimalFactory.createRabbit(Gender.FEMALE);

        cell.addAnimal(wolf1);
        cell.addAnimal(wolf2);
        cell.addAnimal(wolf3);
        cell.addAnimal(rabbit1);
        cell.addAnimal(rabbit2);
        cell2.addAnimal(rabbit3);
        cell2.addAnimal(rabbit4);
        cell2.addAnimal(rabbit5);
        cell2.addAnimal(rabbit6);

        island.setCell(cell);
        island.setCell(cell2);
        island.setCell(cell3);
        island.setCell(cell4);
    }

    private void generateDefaultAnimal() {
        Cell cell = new Cell(0, 0);
        Wolf wolf1 = AnimalFactory.createWolf();
        cell.addAnimal(wolf1);
        island.setCell(cell);
        island.setCell(new Cell(0, 1));
        island.setCell(new Cell(1, 0));
        island.setCell(new Cell(1, 1));
    }
}

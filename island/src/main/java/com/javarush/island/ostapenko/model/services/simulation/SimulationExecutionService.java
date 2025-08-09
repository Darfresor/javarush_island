package com.javarush.island.ostapenko.model.services.simulation;


import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.behavor.AnimalBehavorService;

public class SimulationExecutionService {
    private final Island island;

    public SimulationExecutionService(Island island) {
        this.island = island;
    }

    public void start() {
        AnimalBehavorService service = new AnimalBehavorService();

        for (Cell[] cell : island.getCells()) {
            for (Cell cell1 : cell) {
                if (cell1 != null) {
                    for (Animal animal : cell1.getAnimals()) {
                        service.executeMove(animal, island);
                        service.executeEat(animal, cell1);
                        service.executeReproduce(animal, cell1);
                    }
                }
            }
        }


    }
}

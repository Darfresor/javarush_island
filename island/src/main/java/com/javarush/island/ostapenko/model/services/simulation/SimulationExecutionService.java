package com.javarush.island.ostapenko.model.services.simulation;


import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.behavor.DeathService;
import com.javarush.island.ostapenko.model.services.behavor.FeedingService;
import com.javarush.island.ostapenko.model.services.behavor.MovementService;
import com.javarush.island.ostapenko.model.services.behavor.ReproductionService;

public class SimulationExecutionService {
    private final Island island;
    private final FeedingService feedingService = new FeedingService();
    private final MovementService movementService = new MovementService();
    private final ReproductionService reproductionService = new ReproductionService();
    private final DeathService deathService = new DeathService();


    public SimulationExecutionService(Island island) {
        this.island = island;
    }

    public void start() {


        for (Cell[] cell : island.getCells()) {
            for (Cell cell1 : cell) {
                if (cell1 != null) {
                    for (Animal animal : cell1.getAnimals()) {
                        movementService.executeMove(animal, island);
                        feedingService.executeEat(animal, cell1);
                        reproductionService.executeReproduce(animal, cell1);
                        deathService.executeDeathByEating(animal, cell1);
                        deathService.executeDeathFromStarvation(animal, cell1);
                        deathService.executeDeathDueToOldAge(animal, cell1);
                    }
                    for (Plant plant : cell1.getPlants()) {
                        reproductionService.executeReproduce(plant, cell1);
                        deathService.executeDeathDueToOldAge(plant, cell1);
                    }
                }
            }
        }


    }
}

package com.javarush.island.ostapenko.model.services.simulation;


import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.behavor.DeathService;
import com.javarush.island.ostapenko.model.services.behavor.FeedingService;
import com.javarush.island.ostapenko.model.services.behavor.MovementService;
import com.javarush.island.ostapenko.model.services.behavor.ReproductionService;
import com.javarush.island.ostapenko.model.services.mediator.EventMediator;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;

public class SimulationExecutionService {
    private final Island island;
    private final IMediator mediator = new EventMediator();
    private final FeedingService feedingService = new FeedingService(mediator);
    private final MovementService movementService = new MovementService();
    private final ReproductionService reproductionService = new ReproductionService();
    private final DeathService deathService = new DeathService();



    public SimulationExecutionService(Island island) {
        this.island = island;
    }

    public void start() {
        mediator.subsribe(EventType.ANIMAL_EATEN, deathService);

        for (Cell[] cell : island.getCells()) {
            for (Cell cell1 : cell) {
                if (cell1 != null) {
                    for (Animal animal : cell1.getAnimals()) {
                        //deathService.executeDeathDueToOldAge(animal, cell1);
                        feedingService.executeEat(animal, cell1);
                       // deathService.executeDeathByEating(animal, cell1);
                      //  deathService.executeDeathFromStarvation(animal, cell1);
                      //  movementService.executeMove(animal, island);
                     //   reproductionService.executeReproduce(animal, cell1);


                    }
                    for (Plant plant : cell1.getPlants()) {
                        deathService.executeDeathDueToOldAge(plant, cell1);
                        reproductionService.executeReproduce(plant, cell1);

                    }
                }
            }
        }


    }
}

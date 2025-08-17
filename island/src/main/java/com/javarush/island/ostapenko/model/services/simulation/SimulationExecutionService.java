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

import java.util.List;

public class SimulationExecutionService {
    private final Island island;
    private final IMediator mediator = new EventMediator();
    private final FeedingService feedingService = new FeedingService(mediator);
    private final MovementService movementService = new MovementService();
    private final ReproductionService reproductionService = new ReproductionService(mediator);
    private final DeathService deathService = new DeathService();



    public SimulationExecutionService(Island island) {
        this.island = island;
    }

    public void start() {
        mediator.subsribe(EventType.ANIMAL_EATEN, deathService);
        mediator.subsribe(EventType.ANIMAL_STARVATION, deathService);
        mediator.subsribe(EventType.PLANT_EATEN, deathService);
        mediator.subsribe(EventType.ANIMAL_MOVE_EAT, movementService);
        mediator.subsribe(EventType.ANIMAL_MOVE_REPRODUCE, movementService);

        for (Cell[] cell : island.getGridCopy()) {
            for (Cell cell1 : cell) {
                if (cell1 != null) {
                    for (Animal animal : cell1.getAnimals()) {
                         deathService.executeDeathDueToOldAge(animal, cell1, island);
                        //feedingService.executeEat(animal, cell1, island);
                        //reproductionService.executeReproduce(animal, cell1, island);



                    }
                     for (Plant plant : cell1.getPlants()) {
                        deathService.executeDeathDueToOldAge(plant, cell1, island);
                         // reproductionService.executeReproduce(plant, cell1, island);

                    }
                }
            }
        }

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
        for (int i = 0; i < island.getGridCopy().length; i++) {
            for (int j = 0; j < island.getGridCopy()[0].length; j++) {
                if(island.getGridCopy()[i][j]!=null){
                    List<Plant> listPlant = island.getGridCopy()[i][j].getPlants();
                    System.out.println(String.format("Клетка [%d:%d] содержит растения: %s",i, j
                            ,listPlant
                    ));
                }else{
                    System.out.println(String.format("Клетка [%d:%d] без растений",i, j));
                }

            }
        }


    }
}

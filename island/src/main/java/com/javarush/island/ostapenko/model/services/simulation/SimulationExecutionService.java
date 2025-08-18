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
import com.javarush.island.ostapenko.util.Logger;

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

        Logger.logIslandComposition(island);
        Logger.flush();

        //deathService.executeDeathDueToOldAge(island);
        feedingService.executeEat(island);


        Logger.logIslandComposition(island);
        Logger.flush();


    }
}

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
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.EventMediator;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.util.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class SimulationExecutionService {
    private final Phaser phaser = new Phaser(1);
    private final ModelThreadPoolManager modelThreadPoolManager = new ModelThreadPoolManager(phaser);
    private final Island island;
    private final IMediator mediator = new EventMediator();
    private final FeedingService feedingService = new FeedingService(mediator, modelThreadPoolManager);
    private final MovementService movementService = new MovementService(mediator, modelThreadPoolManager);
    private final ReproductionService reproductionService = new ReproductionService(mediator, modelThreadPoolManager);
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
        mediator.subsribe(EventType.ANIMAL_EAT, feedingService);

        Logger.logIslandComposition(island);
        Logger.flush();

        modelThreadPoolManager.executeCoreTask(()->deathService.executeDeathDueToOldAge(island));
        modelThreadPoolManager.executeCoreTask(()->feedingService.executeEat(island));
        modelThreadPoolManager.executeCoreTask(()->reproductionService.executeReproduce(island));

        modelThreadPoolManager.waitForAllTask();
        Logger.logIslandComposition(island);
        Logger.flush();


    }
}

package com.javarush.island.ostapenko.model.services.simulation;


import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.behavor.*;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.EventMediator;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.util.Logger;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationExecutionService {
    private final Phaser phaser = new Phaser(1);
    private final ModelThreadPoolManager modelThreadPoolManager = new ModelThreadPoolManager(phaser);
    private final Island island;
    private final IMediator mediator = new EventMediator();
    private final StatisticsService statisticsService = new StatisticsService();
    private final FeedingService feedingService = new FeedingService(mediator, modelThreadPoolManager);
    private final MovementService movementService = new MovementService(mediator, modelThreadPoolManager);
    private final ReproductionService reproductionService = new ReproductionService(mediator, modelThreadPoolManager);
    private final DeathService deathService = new DeathService(mediator, modelThreadPoolManager);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AtomicBoolean isCycleRunning = new AtomicBoolean(false);


    public SimulationExecutionService(Island island) {
        this.island = island;
    }

    public void start(long initialDelay, long period, TimeUnit unit) {

        statisticsService.intializeFromIsland(island);
        statisticsService.printStatistics();
        System.out.println("Запускаем цикл симуляций");

        mediator.subsribe(EventType.ANIMAL_DEATH_BY_OLD, statisticsService);
        mediator.subsribe(EventType.PLANT_DEATH_BY_OLD, statisticsService);
        mediator.subsribe(EventType.ANIMAL_EATEN, deathService);
        mediator.subsribe(EventType.ANIMAL_EATEN, statisticsService);
        mediator.subsribe(EventType.ANIMAL_STARVATION, deathService);
        mediator.subsribe(EventType.ANIMAL_STARVATION, statisticsService);
        mediator.subsribe(EventType.PLANT_EATEN, deathService);
        mediator.subsribe(EventType.PLANT_EATEN,statisticsService);
        mediator.subsribe(EventType.ANIMAL_MOVE_EAT, movementService);
        mediator.subsribe(EventType.ANIMAL_MOVE_REPRODUCE, movementService);
        mediator.subsribe(EventType.ANIMAL_EAT, feedingService);


        scheduler.scheduleAtFixedRate(() -> executeOneMoveOfSimulation(), initialDelay, period, unit);

    }

    private void executeOneMoveOfSimulation() {
        if (isCycleRunning.get()) {
            System.err.println("Предшествующий день еще симулируется, пропускаем цикл");
            return;
        }
        isCycleRunning.set(true);
        try {
            System.out.println("🟢 НАЧАЛО ЦИКЛА " + LocalDateTime.now());
            executeCoreThread();
            System.out.println("✅ ЗАВЕРШЕНИЕ ЦИКЛА " + LocalDateTime.now());
        }  finally {
            isCycleRunning.set(false);
        }
    }

    private void executeCoreThread() {
        Logger.logIslandComposition(island);
        Logger.flush();

        modelThreadPoolManager.executeCoreTask(() -> deathService.executeDeathDueToOldAge(island));
        modelThreadPoolManager.executeCoreTask(() -> feedingService.executeEat(island));
        modelThreadPoolManager.executeCoreTask(() -> reproductionService.executeReproduce(island));

        modelThreadPoolManager.waitForAllTask();
        Logger.logIslandComposition(island);
        Logger.flush();

        statisticsService.printStatistics();
    }

}

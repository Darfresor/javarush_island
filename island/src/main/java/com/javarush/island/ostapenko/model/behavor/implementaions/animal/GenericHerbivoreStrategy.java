package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.model.behavor.EatingRules;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalStarvationEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.PlantEatenEvent;
import com.javarush.island.ostapenko.util.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class GenericHerbivoreStrategy implements Eatable {
    private final IMediator mediator;

    public GenericHerbivoreStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void eat(Animal eater, Cell cell, Island island, ExecutorService movementServiceThread) {
        for (Plant target : cell.getPlants()) {
            if (EatingRules.canEat(eater.getClass(), target.getClass())) {
                double probability = EatingRules.getEatProbability(eater.getClass(), target.getClass());
                Logger.logFeedingService(eater, cell, String.format("%s нашел существо %s, вероятность съесть его = %f процентов",
                        eater.getSpeciesName(), target.getSpeciesName(), probability));

                if (ThreadLocalRandom.current().nextDouble() < probability) {
                    Logger.logFeedingService(eater, cell, String.format("%s съел %s",
                            eater.getSpeciesName(), target.getSpeciesName()));
                    mediator.notify(new PlantEatenEvent(eater, target, cell));
                    eater.setSatiety(calculateSatiety(eater, target));
                    Logger.logFeedingService(eater, cell, String.format("Сытость %s = %f",
                            eater.getSpeciesName(), eater.getSatiety()));
                    return;
                } else {
                    Logger.logFeedingService(eater, cell, String.format("%s не смог съесть %s",
                            eater.getSpeciesName(), target.getSpeciesName()));
                    if (checkDeathByStarvation(eater, cell, island)) return;
                    return;
                }

            }
        }
        Logger.logFeedingService(eater, cell, String.format("%s не нашел еды в этой клетке",
                eater.getSpeciesName()));
        if (checkDeathByStarvation(eater, cell, island)) return;
        mediator.notify(new AnimalMoveEvent(eater, cell, island));
    }

    private boolean checkDeathByStarvation(Animal eater, Cell cell, Island island) {
        reduceSatiety(eater);
        Logger.logFeedingService(eater, cell, String.format("Сытость %s = %f",
                eater.getSpeciesName(), eater.getSatiety()));
        if (eater.getSatiety() == 0) {
            mediator.notify(new AnimalStarvationEvent(eater, cell, island));
            return true;
        }
        return false;
    }

    private float calculateNutrition(Animal eater, Plant target) {
        return target.getWeightInKg() / eater.getFoodToBeFullySatiatedInKg();
    }

    private float calculateSatiety(Animal eater, Plant target) {
        float satiety = eater.getSatiety() + calculateNutrition(eater, target);
        if (satiety > 1.0f) {
            return 1.0f;
        } else {
            return satiety;
        }
    }

    private void reduceSatiety(Animal eater) {
        float satiety = eater.getSatiety() - 0.3f;
        if (satiety > 0.0f) {
            eater.setSatiety(satiety);
        } else {
            eater.setSatiety(0.0f);
        }
    }
}


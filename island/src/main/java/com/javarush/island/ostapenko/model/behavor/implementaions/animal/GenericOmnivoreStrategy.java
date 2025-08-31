package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.core.exception.ApplicationException;
import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.behavor.EatingRules;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatenEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveForEatEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalStarvationEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.PlantEatenEvent;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class GenericOmnivoreStrategy implements Eatable {
    private final IMediator mediator;

    public GenericOmnivoreStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void eat(Animal eater, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
       boolean  plantSuccessfullyEaten = eatPlant(eater, cell, island, modelThreadPoolManager);
       if(!plantSuccessfullyEaten){
           boolean  animalSuccessfullyEaten = eatAnimal(eater, cell, island, modelThreadPoolManager);
           if(!animalSuccessfullyEaten){
               Logger.logFeedingService(eater, cell, String.format("%s не нашел еды в этой клетке",
                       eater.getSpeciesName()));
               if (checkDeathByStarvation(eater, cell, island, modelThreadPoolManager)) return;
               modelThreadPoolManager.executeMoveTask(() -> mediator.notify(new AnimalMoveForEatEvent(eater, cell, island)));
           }
       };
    }

    public boolean eatPlant(Animal eater, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        Animal currentEater = cell.getAnimalById(eater.getId());
        if (currentEater == null || currentEater.isBeingEaten()) return false;
        for (UUID plantId : cell.getPlantIds()) {
            Plant target = cell.getPlantById(plantId);
            if (target == null || target.isBeingEaten()) continue;
            if (EatingRules.canEat(eater.getClass(), target.getClass())) {
                double probability = EatingRules.getEatProbability(eater.getClass(), target.getClass());
                Logger.logFeedingService(eater, cell, String.format("%s нашел существо %s, вероятность съесть его = %f процентов",
                        eater.getSpeciesName(), target.getSpeciesName(), probability));
                if (ThreadLocalRandom.current().nextDouble() < probability) {
                    Logger.logFeedingService(eater, cell, String.format("%s съел %s",
                            eater.getSpeciesName(), target.getSpeciesName()));
                    modelThreadPoolManager.executeDeathTask(() -> mediator.notify(new PlantEatenEvent(eater, target, cell)));
                    eater.setSatiety(calculateSatiety(eater, target));
                    Logger.logFeedingService(eater, cell, String.format("Сытость %s = %f",
                            eater.getSpeciesName(), eater.getSatiety()));
                    return true;
                } else {
                    Logger.logFeedingService(eater, cell, String.format("%s не смог съесть %s",
                            eater.getSpeciesName(), target.getSpeciesName()));
                    if (checkDeathByStarvation(eater, cell, island, modelThreadPoolManager)) return false;
                }
                return false;
            }
        }
        return false;
    }

    public boolean eatAnimal(Animal eater, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        for (UUID animalId : cell.getAnimalIds()) {
            Animal target = cell.getAnimalById(animalId);
            if (target == null || target.isBeingEaten()) continue;
            if (EatingRules.canEat(eater.getClass(), target.getClass())) {
                double probability = EatingRules.getEatProbability(eater.getClass(), target.getClass());
                Logger.logFeedingService(eater, cell, String.format("%s нашел существо %s, вероятность съесть его = %f процентов",
                        eater.getSpeciesName(), target.getSpeciesName(), probability));

                if (ThreadLocalRandom.current().nextDouble() < probability) {
                    if (target.markAsEaten()) {
                        Logger.logFeedingService(eater, cell, String.format("%s съел %s",
                                eater.getSpeciesName(), target.getSpeciesName()));
                        modelThreadPoolManager.executeDeathTask(() -> mediator.notify(new AnimalEatenEvent(eater, target, cell)));
                        eater.setSatiety(calculateSatiety(eater, target));
                        Logger.logFeedingService(eater, cell, String.format("Сытость %s = %f",
                                eater.getSpeciesName(), eater.getSatiety()));
                        return true;
                    }

                } else {
                    Logger.logFeedingService(eater, cell, String.format("%s не смог съесть %s",
                            eater.getSpeciesName(), target.getSpeciesName()));
                    if (checkDeathByStarvation(eater, cell, island, modelThreadPoolManager)) return false;
                    return false;
                }
            }
        }
        return false;
    }


    private boolean checkDeathByStarvation(Animal eater, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager) {
        reduceSatiety(eater);
        Logger.logFeedingService(eater, cell, String.format("Сытость %s = %f",
                eater.getSpeciesName(), eater.getSatiety()));
        if (eater.getSatiety() == 0) {
            modelThreadPoolManager.executeDeathTask(() -> mediator.notify(new AnimalStarvationEvent(eater, cell, island)));
            return true;
        }
        return false;
    }


    private float calculateNutrition(Animal eater, Animal target) {
        return target.getWeightInKg() / eater.getFoodToBeFullySatiatedInKg();
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
    private float calculateSatiety(Animal eater, Animal target) {
        float satiety = eater.getSatiety() + calculateNutrition(eater, target);
        if (satiety > 1.0f) {
            return 1.0f;
        } else {
            return satiety;
        }
    }

    private  void reduceSatiety(Animal eater) {
        float satiety = eater.getSatiety() - 0.15f;
        if (satiety > 0.0f) {
            eater.setSatiety(satiety);
        } else {
            eater.setSatiety(0.0f);
        }
    }
}

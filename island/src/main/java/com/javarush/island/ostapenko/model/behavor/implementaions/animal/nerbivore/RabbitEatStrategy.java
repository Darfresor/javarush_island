package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.EatingRules;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatenEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalStarvationEvent;

import java.util.concurrent.ThreadLocalRandom;

public class RabbitEatStrategy implements Eatable {
    private final IMediator mediator;

    public RabbitEatStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void eat(Animal eater, Cell cell, Island island) {
        for (Plant target : cell.getPlants()) {
            if (EatingRules.canEat(eater.getClass(), target.getClass())) {
                double probability = EatingRules.getEatProbability(eater.getClass(), target.getClass());
                System.out.println(String.format("вероятность съесть растение %s составляет - %f процентов"
                        , target.getClass().getSimpleName(), probability));

                if (ThreadLocalRandom.current().nextDouble() < probability) {
                    System.out.println("Кролик съел " + target.getSpeciesName());
                    //mediator.notify(new AnimalEatenEvent(eater, target, cell));
                    eater.setSatiety(calculateSatiety(eater, target));
                    System.out.println("Голод кролика = " + eater.getSatiety());
                    return;
                } else {
                    System.out.println("Волк не смог съесть " + target.getSpeciesName());
                    System.out.println("Голод волка = " + eater.getSatiety());
                    if (checkDeathByStarvation(eater, cell, island)) return;
                    return;
                }

            }
        }
        System.out.println("Кролик не нашел еды в этой клетке");
        if (checkDeathByStarvation(eater, cell, island)) return;
        mediator.notify(new AnimalMoveEvent(eater, cell, island));
    }

    private boolean checkDeathByStarvation(Animal eater, Cell cell, Island island) {
        reduceSatiety(eater);
        System.out.println("Голод кролика = " + eater.getSatiety());
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

package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.EatingRules;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalEatenEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveEvent;

import java.util.concurrent.ThreadLocalRandom;

public class WolfEatStrategy implements Eatable {
    private final IMediator mediator;

    public WolfEatStrategy(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void eat(Animal eater, Cell cell, Island island) {
        reduceSatiety(eater);
        System.out.println("Голод волка = " + eater.getSatiety());
        for (Animal target : cell.getAnimals()) {
            if (EatingRules.canEat(eater.getClass(), target.getClass())) {
                double probability = EatingRules.getEatProbability(eater.getClass(), target.getClass());
                System.out.println(String.format("вероятность съесть существо %s составляет - %f процентов"
                        , target.getClass().getSimpleName(), probability));

                if (ThreadLocalRandom.current().nextDouble() < probability) {
                    System.out.println("Волк съел " + target.getSpeciesName());
                    mediator.notify(new AnimalEatenEvent(eater, target, cell));
                    eater.setSatiety(calculateSatiety(eater, target));
                    System.out.println("Голод волка = " + eater.getSatiety());
                    break;
                } else {
                    System.out.println("Волк не смог съесть " + target.getSpeciesName());
                    System.out.println("Голод волка = " + eater.getSatiety());
                    break;
                }

            }
        }
        System.out.println("Волк не нашел еды в этой клетке");
        mediator.notify(new AnimalMoveEvent(eater, cell, island));
    }

    private float calculateNutrition(Animal eater, Animal target) {
        return target.getWeightInKg() / eater.getFoodToBeFullySatiatedInKg();
    }

    private float calculateSatiety(Animal eater, Animal target) {
        float nutrition = calculateNutrition(eater, target);
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

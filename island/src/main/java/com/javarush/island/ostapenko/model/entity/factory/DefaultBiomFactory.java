package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.entity.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultBiomFactory {
    private DefaultBiomFactory() {
    }

    ;


    public static List<Animal> createAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        List<Animal> predators = createPredators();
        animals.addAll(predators);
        return animals;
    }


    public static List<Animal> createHerbivores() {
        return List.of();
    }


    public static List<Animal> createPredators() {
        List<Animal> predators = new ArrayList<>();

        List<Wolf> wolfs = AnimalFactory.createWolfs(
                ThreadLocalRandom.current().nextInt(
                        Wolf.getMaxNumberOfAnimalInCell()
                )
        );
        predators.addAll(wolfs);
        return predators;
    }


    public static List<Animal> createOmnivores() {
        return List.of();
    }


    public static List<Plant> createAllPlants() {
        return List.of();
    }

}

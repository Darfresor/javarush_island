package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Duck;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.entity.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultBiomFactory {
    private DefaultBiomFactory() {
    }



    public static List<Animal> createAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        List<Animal> predators = createPredators();
        List<Animal> herbivores = createHerbivores();
        List<Animal> omnivores = createOmnivores();
        animals.addAll(predators);
        animals.addAll(herbivores);
        animals.addAll(omnivores);
        return animals;
    }


    public static List<Animal> createHerbivores() {
        List<Animal> predators = new ArrayList<>();

        List<Rabbit> rabbits = AnimalFactory.createRabbits(
                ThreadLocalRandom.current().nextInt(
                        Rabbit.getMaxNumberOfRabbitInCell()
                )
        );
        predators.addAll(rabbits);
        return predators;
    }


    public static List<Animal> createPredators() {
        List<Animal> predators = new ArrayList<>();

        List<Wolf> wolfs = AnimalFactory.createWolfs(
                ThreadLocalRandom.current().nextInt(
                        Wolf.getMaxNumberOfWolfInCell()
                )
        );
        predators.addAll(wolfs);
        return predators;
    }


    public static List<Animal> createOmnivores() {
        List<Animal> omnivores = new ArrayList<>();

        List<Duck> ducks = AnimalFactory.createDucks(
                ThreadLocalRandom.current().nextInt(
                        Duck.getMaxNumberOfDuckInCell()
                )
        );
        omnivores.addAll(ducks);
        return omnivores;
    }


    public static List<Plant> createAllPlants() {
        return List.of();
    }

}

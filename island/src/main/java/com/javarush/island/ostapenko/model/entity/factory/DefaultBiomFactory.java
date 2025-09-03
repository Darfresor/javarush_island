package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.*;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Boar;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Mouse;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.insetcs.Caterpillar;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Duck;
import com.javarush.island.ostapenko.model.entity.animal.predator.*;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
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
        List<Animal> herbivores = new ArrayList<>();

        List<Rabbit> rabbits = AnimalFactory.createRabbits(
                ThreadLocalRandom.current().nextInt(
                        Rabbit.getMaxNumberOfRabbitInCell()
                )
        );
        List<Caterpillar> caterpillars = AnimalFactory.createCaterpillars(
                ThreadLocalRandom.current().nextInt(
                        Caterpillar.getMaxNumberOfCaterpillarInCell()
                )
        );
        List<Horse> horses = AnimalFactory.createHorses(
                ThreadLocalRandom.current().nextInt(
                        Horse.getMaxNumberOfHorseInCell()
                )
        );
        List<Deer> deers = AnimalFactory.createDeers(
                ThreadLocalRandom.current().nextInt(
                        Deer.getMaxNumberOfDeerInCell()
                )
        );
        List<Goat> goats = AnimalFactory.createGoats(
                ThreadLocalRandom.current().nextInt(
                        Goat.getMaxNumberOfGoatInCell()
                )
        );
        List<Sheep> sheeps = AnimalFactory.createSheeps(
                ThreadLocalRandom.current().nextInt(
                        Sheep.getMaxNumberOfSheepInCell()
                )
        );
        List<Buffalo> buffalos = AnimalFactory.createBuffalos(
                ThreadLocalRandom.current().nextInt(
                        Buffalo.getMaxNumberOfBuffaloInCell()
                )
        );

        herbivores.addAll(rabbits);
        herbivores.addAll(caterpillars);
        herbivores.addAll(horses);
        herbivores.addAll(deers);
        herbivores.addAll(goats);
        herbivores.addAll(sheeps);
        herbivores.addAll(buffalos);
        return herbivores;
    }


    public static List<Animal> createPredators() {
        List<Animal> predators = new ArrayList<>();

        List<Wolf> wolfs = AnimalFactory.createWolfs(
                ThreadLocalRandom.current().nextInt(
                        Wolf.getMaxNumberOfWolfInCell()
                )
        );
        List<Boa> boas = AnimalFactory.createBoas(
                ThreadLocalRandom.current().nextInt(
                        Boa.getMaxNumberOfBoaInCell()
                )
        );
        List<Fox> foxs = AnimalFactory.createFoxs(
                ThreadLocalRandom.current().nextInt(
                        Fox.getMaxNumberOfFoxInCell()
                )
        );
        List<Bear> bears = AnimalFactory.createBears(
                ThreadLocalRandom.current().nextInt(
                        Bear.getMaxNumberOfBearInCell()
                )
        );
        List<Eagle> eagles = AnimalFactory.createEagles(
                ThreadLocalRandom.current().nextInt(
                        Eagle.getMaxNumberOfEagleInCell()
                )
        );
        predators.addAll(wolfs);
        predators.addAll(boas);
        predators.addAll(foxs);
        predators.addAll(bears);
        predators.addAll(eagles);
        return predators;
    }


    public static List<Animal> createOmnivores() {
        List<Animal> omnivores = new ArrayList<>();

        List<Duck> ducks = AnimalFactory.createDucks(
                ThreadLocalRandom.current().nextInt(
                        Duck.getMaxNumberOfDuckInCell()
                )
        );
        List<Mouse> mouses = AnimalFactory.createMouses(
                ThreadLocalRandom.current().nextInt(
                        Mouse.getMaxNumberOfMouseInCell()
                )
        );
        List<Boar> boars = AnimalFactory.createBoars(
                ThreadLocalRandom.current().nextInt(
                        Boar.getMaxNumberOfBoarInCell()
                )
        );
        omnivores.addAll(ducks);
        omnivores.addAll(mouses);
        omnivores.addAll(boars);
        return omnivores;
    }


    public static List<Plant> createAllPlants() {
        List<Plant> plants = new ArrayList<>();

        List<Dandelion> dandelions = PlantFactory.createDandelions(
                Dandelion.getMaxNumberOfDandelionInCell()
        );
        plants.addAll(dandelions);
        return plants;
    }

}

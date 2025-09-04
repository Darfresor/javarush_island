package com.javarush.island.ostapenko.model.island;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Cell {
    private final Object lock = new Object();
    private final Object plantModificationLock = new Object();
    private final int x;
    private final int y;
    private final ConcurrentHashMap<UUID, Animal> animals = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<UUID,Plant> plants = new ConcurrentHashMap<>();


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int addPlantsAtomicallyWithCount(Plant existingPlant, List<Plant> newPlants) {
        synchronized (plantModificationLock) {
            if (!plants.containsKey(existingPlant.getId())) {
                return 0;
            }

            long currentCount = plants.values().stream()
                    .filter(p -> p.getClass() == existingPlant.getClass())
                    .count();
            int maxAllowed = existingPlant.getMaxNumberOfPlantInCell();
            int availableSpace = maxAllowed - (int) currentCount;


            if (availableSpace <= 0) {
                return 0;
            }
            int plantsToAdd = Math.min(availableSpace, newPlants.size());

            int actuallyAdded = 0;
            for (int i = 0; i < plantsToAdd; i++) {
                Plant newPlant = newPlants.get(i);
                if (plants.putIfAbsent(newPlant.getId(), newPlant) == null) {
                    actuallyAdded++;
                }
            }

            return actuallyAdded;
        }
    }

    public void addAnimals(List<Animal> animals){
        for (Animal animal : animals) {
            this.animals.putIfAbsent(animal.getId(), animal);
        }
    }
    public void addPlants(List<Plant> plants){
        for (Plant plant : plants) {
            this.plants.putIfAbsent(plant.getId(), plant);
        }
    }

    public boolean moveAnimalTo(Animal animal, Cell destination) {
        synchronized (lock) {
            if (animals.remove(animal.getId(), animal)) {
                return destination.addAnimal(animal);
            }
            return false;
        }
    }

    public boolean  addAnimal(Animal animal){
        return animals.putIfAbsent(animal.getId(), animal) == null;
    }
    public boolean removeAnimal(Animal animal){
        return animals.remove(animal.getId(), animal);
    }
    /*
    public Collection<Animal> getAnimals(){
        return animals.values();
    }*/
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals.values());
    }
    public boolean addPlant(Plant plant){
        return plants.putIfAbsent(plant.getId(), plant) == null;
    }
    public boolean removePlant(Plant plant){

        return plants.remove(plant.getId(), plant);
    }

    public Collection<Plant> getPlants(){
        return plants.values();
    }

    public UUID[] getAnimalIds() {
        return animals.keySet().toArray(new UUID[0]);
    }


    public Animal getAnimalById(UUID id) {
        return animals.get(id);
    }
    public UUID[] getPlantIds() {
        return plants.keySet().toArray(new UUID[0]);
    }

    public Plant getPlantById(UUID id) {
        return plants.get(id);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", animals=" + animals +
                ", plants=" + plants +
                '}';
    }
}

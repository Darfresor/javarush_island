package com.javarush.island.ostapenko.model.island;

import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.plant.Plant;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
    private final int x;
    private final int y;
    private List<Animal> animals = new CopyOnWriteArrayList<>();;
    private List<Plant> plants = new CopyOnWriteArrayList<>();;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean  addAnimal(Animal animal){
        return animals.add(animal);
    }
    public boolean removeAnimal(Animal animal){
        return animals.remove(animal);
    }

    public List<Animal> getAnimals(){
        return  Collections.unmodifiableList(animals);
    }
    public boolean addPlant(Plant plant){
        return plants.add(plant);
    }
    public boolean removePlant(Plant plant){
        return plants.remove(plant);
    }

    public List<Plant> getPlants(){
        return Collections.unmodifiableList(plants);
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

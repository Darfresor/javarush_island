package com.javarush.island.ostapenko.model.island;

import com.javarush.island.ostapenko.model.entity.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int x;
    private int y;
    private List<Animal> animals = new ArrayList<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
    }
    public List<Animal> getAnimals(){
        return animals;
    }
}

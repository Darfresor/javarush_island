package com.javarush.island.ostapenko.model.entity.plant;


import com.javarush.island.ostapenko.model.entity.Creature;

import java.util.concurrent.atomic.AtomicLong;
public abstract class Plant extends Creature {
    private static AtomicLong uniqueObjectId= new AtomicLong(0);;
    protected String speciesName;
    protected int age;
    protected int maxAge;
    protected float weightInKg;
    protected float maxWeightInKg;
    protected int maxNumberOfPlantInCell;

    public Plant(String speciesName, int age, int maxAge, float weightInKg, float maxWeightInKg, int maxNumberOfPlantInCell) {
        this.speciesName = speciesName;
        this.age = age;
        this.maxAge = maxAge;
        this.weightInKg = weightInKg;
        this.maxWeightInKg = maxWeightInKg;
        this.maxNumberOfPlantInCell = maxNumberOfPlantInCell;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "Plant classs='" + this.getClass().getSimpleName() + '\'' +
                '}';
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public float getWeightInKg() {
        return weightInKg;
    }

    public float getMaxWeightInKg() {
        return maxWeightInKg;
    }

    public int getMaxNumberOfPlantInCell() {
        return maxNumberOfPlantInCell;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}

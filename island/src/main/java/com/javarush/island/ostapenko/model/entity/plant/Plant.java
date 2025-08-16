package com.javarush.island.ostapenko.model.entity.plant;


import com.javarush.island.ostapenko.model.entity.Creature;

import java.util.concurrent.atomic.AtomicLong;
public abstract class Plant extends Creature {
    private static AtomicLong uniqueObjectId= new AtomicLong(0);;
    protected String speciesName;
    protected int ageInDay;
    protected int maxAgeInDay;
    protected float weightInKg;
    protected float maxWeightInKg;
    protected int maxNumberOfPlantInCell;

    public Plant(String speciesName, int ageInDay, int maxAgeInDay, float weightInKg, float maxWeightInKg, int maxNumberOfPlantInCell) {
        this.speciesName = speciesName;
        this.ageInDay = ageInDay;
        this.maxAgeInDay = maxAgeInDay;
        this.weightInKg = weightInKg;
        this.maxWeightInKg = maxWeightInKg;
        this.maxNumberOfPlantInCell = maxNumberOfPlantInCell;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "Plant classs='" + this.getClass().getSimpleName() +":"  + this.hashCode() + '\'' +
                '}';
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public int getAgeInDay() {
        return ageInDay;
    }

    public int getMaxAgeInDay() {
        return maxAgeInDay;
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

    public void setAgeInDay(int ageInDay) {
        this.ageInDay = ageInDay;
    }
}

package com.javarush.island.ostapenko.model.entity.animal;

import com.javarush.island.ostapenko.constants.Gender;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Animal extends Creature{
    private static AtomicLong uniqueObjectId= new AtomicLong(0);;
    protected String speciesName;
    protected int age;
    protected int maxAge;
    protected Gender gender;
    protected float weightInKg;
    protected float maxWeightInKg;
    protected int maxNumberOfAnimalInCell;
    protected int cellsPerTurnSpeed;
    protected float satiety;
    protected float foodToBeFullySatiatedInKg;

    public Animal(String speciesName, int age, int maxAge, Gender gender, float weightInKg,
                  float maxWeightInKg, int cellsPerTurnSpeed, float foodToBeFullySatiatedInKg, float satiety){
        this.speciesName = speciesName;
        this.age =age;
        this.maxAge= maxAge;
        this.gender = gender;
        this.weightInKg = weightInKg;
        this.maxWeightInKg = maxWeightInKg;
        this.cellsPerTurnSpeed = cellsPerTurnSpeed;
        this.foodToBeFullySatiatedInKg = foodToBeFullySatiatedInKg;
        this.satiety = satiety;
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

    public Gender getGender() {
        return gender;
    }

    public float getWeightInKg() {
        return weightInKg;
    }

    public float getMaxWeightInKg() {
        return maxWeightInKg;
    }

    public int getMaxNumberOfAnimalInCell() {
        return maxNumberOfAnimalInCell;
    }

    public int getCellsPerTurnSpeed() {
        return cellsPerTurnSpeed;
    }

    public float getFoodToBeFullySatiatedInKg() {
        return foodToBeFullySatiatedInKg;
    }

    public float getSatiety() {
        return satiety;
    }

    public void setSatiety(float satiety) {
        this.satiety = satiety;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "Animal classs='" + this.getClass().getSimpleName() + '\'' +
                '}';
    }
}

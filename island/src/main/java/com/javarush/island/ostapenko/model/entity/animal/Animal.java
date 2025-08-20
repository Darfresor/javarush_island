package com.javarush.island.ostapenko.model.entity.animal;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.Creature;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Animal extends Creature {
    private final UUID id;

    private final AtomicBoolean isBeingEaten = new AtomicBoolean(false);
    protected String speciesName;
    protected int ageInDay;
    protected int maxAgeInDay;
    protected Gender gender;
    protected float weightInKg;
    protected float maxWeightInKg;
    protected int maxNumberOfAnimalInCell;
    protected int cellsPerTurnSpeed;
    protected int cellsLeftInCurrentTurn;
    protected float satiety;
    protected float foodToBeFullySatiatedInKg;
    protected AtomicBoolean reprocudedInCurrentTurn = new AtomicBoolean(false);
    protected DietType dietType;

    public Animal(String speciesName, int ageInDay, int maxAgeInDay, Gender gender, float weightInKg,
                  float maxWeightInKg, int cellsPerTurnSpeed, float foodToBeFullySatiatedInKg, float satiety, DietType dietType){
        this.speciesName = speciesName;
        this.ageInDay = ageInDay;
        this.maxAgeInDay = maxAgeInDay;
        this.gender = gender;
        this.weightInKg = weightInKg;
        this.maxWeightInKg = maxWeightInKg;
        this.cellsPerTurnSpeed = cellsPerTurnSpeed;
        this.foodToBeFullySatiatedInKg = foodToBeFullySatiatedInKg;
        this.satiety = satiety;
        this.dietType = dietType;

        this.id = UUID.randomUUID();
        this.cellsLeftInCurrentTurn = cellsPerTurnSpeed;
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

    public int getCellsLeftInCurrentTurn() {
        return cellsLeftInCurrentTurn;
    }

    public void setCellsLeftInCurrentTurn(int cellsLeftInCurrentTurn) {
        this.cellsLeftInCurrentTurn = cellsLeftInCurrentTurn;
    }

    public void setAgeInDay(int ageInDay) {
        this.ageInDay = ageInDay;
    }

    public boolean getReprocudedInCurrentTurn() {
        return reprocudedInCurrentTurn.get();
    }

    public void setReprocudedInCurrentTurn(boolean b) {
        reprocudedInCurrentTurn.set(b);
    }


    public DietType getDietType() {
        return dietType;
    }

    public boolean markAsEaten() {
        return isBeingEaten.compareAndSet(false, true);
    }

    public boolean isBeingEaten() {
        return isBeingEaten.get();
    }
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "Animal classs='" + this.getClass().getSimpleName() +":"  + this.hashCode() + '\'' +
                '}';
    }
}

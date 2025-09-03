package com.javarush.island.ostapenko.model.entity.plant;


import com.javarush.island.ostapenko.model.entity.Creature;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
public abstract class Plant extends Creature {
    private final UUID id;
    private final AtomicBoolean isBeingEaten = new AtomicBoolean(false);
    protected String speciesName;
    protected int ageInDay;
    protected int maxAgeInDay;
    protected float weightInKg;
    protected float maxWeightInKg;
    protected AtomicBoolean reprocudedInCurrentTurn = new AtomicBoolean(false);


    public Plant(String speciesName, int ageInDay, int maxAgeInDay, float weightInKg, float maxWeightInKg) {
        this.speciesName = speciesName;
        this.ageInDay = ageInDay;
        this.maxAgeInDay = maxAgeInDay;
        this.weightInKg = weightInKg;
        this.maxWeightInKg = maxWeightInKg;

        this.id = UUID.randomUUID();
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

    public abstract int getMaxNumberOfPlantInCell();

    public boolean markAsEaten() {
        return isBeingEaten.compareAndSet(false, true);
    }

    public boolean isBeingEaten() {
        return isBeingEaten.get();
    }

    public UUID getId() {
        return id;
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
}

package com.javarush.island.ostapenko.model.entity.plant;


import com.javarush.island.ostapenko.model.entity.animal.Creature;

import java.util.concurrent.atomic.AtomicLong;
public abstract class Plant extends Creature {
    private static AtomicLong uniqueObjectId= new AtomicLong(0);;
    protected String speciesName;
    protected byte age;
    protected byte maxAge;
    protected float weightInKg;
    protected float maxWeightInKg;
    protected short maxNumberOfPlantInCell;

    @Override
    public String toString() {
        return "Plant{" +
                "Plant classs='" + this.getClass().getSimpleName() + '\'' +
                '}';
    }
}

package com.javarush.island.ostapenko.model.entity.animal;

import com.javarush.island.ostapenko.constants.Gender;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Animal{
    private static AtomicLong uniqueObjectId= new AtomicLong(0);;
    protected String speciesName;
    protected byte age;
    protected byte maxAge;
    protected Gender gender;
    protected float weightInKg;
    protected float maxWeightInKg;
    protected short maxNumberOfAnimalInCell;
    protected short cellsPerTurnSpeed;
    protected float foodToBeFullySatiatedInKg;

    @Override
    public String toString() {
        return "Animal{" +
                "Animal classs='" + this.getClass().getSimpleName() + '\'' +
                '}';
    }
}

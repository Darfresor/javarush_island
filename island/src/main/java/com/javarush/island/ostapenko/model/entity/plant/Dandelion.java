package com.javarush.island.ostapenko.model.entity.plant;

public class Dandelion extends Plant{
    private final static int MAX_AGE_DAYS = 3 * 365;
    private final static float MAX_WEIGHT_IN_KG = 50;
    private final static int MAX_NUMBER_OF_PLANT_IN_CELL = 200;
    public Dandelion(String speciesName, int age,  float weightInKg) {
        super(speciesName, age, MAX_AGE_DAYS, weightInKg, MAX_WEIGHT_IN_KG, MAX_NUMBER_OF_PLANT_IN_CELL);
    }
}

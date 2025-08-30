package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;

public class PlantFactory {
    private PlantFactory() {
    }


    public static Dandelion createPlant(int age) {
        return new Dandelion("Одуванчик",age,1f);
    }
    public static Dandelion createPlant() {
        return new Dandelion("Одуванчик",(int) (Math.random() * 365*2) + 1,1f);
    }
}

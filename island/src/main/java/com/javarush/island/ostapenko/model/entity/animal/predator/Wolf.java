package com.javarush.island.ostapenko.model.entity.animal.predator;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.Animal;

public class Wolf extends Animal {
    private final static int MAX_AGE_DAYS = 10 * 365;
    private final static float MAX_WEIGHT_IN_KG = 50;
    private final static int CELL_PER_TURN = 3;
    private final static float FOOD_TO_BE_SATIATED_IN_KG =8;

    public Wolf(String speciesName, int age, Gender gender, float weightInKg,
                  float satiety, DietType dietType) {
        super(speciesName, age, MAX_AGE_DAYS, gender, weightInKg, MAX_WEIGHT_IN_KG, CELL_PER_TURN, FOOD_TO_BE_SATIATED_IN_KG, satiety, dietType);
    }

}

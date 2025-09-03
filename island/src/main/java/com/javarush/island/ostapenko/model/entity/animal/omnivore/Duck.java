package com.javarush.island.ostapenko.model.entity.animal.omnivore;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.Animal;

public class Duck extends Animal {
    private static final int MAX_AGE_DAYS = 10 * 365;
    private static final float MAX_WEIGHT_IN_KG = 1;
    private static final int CELL_PER_TURN = 4;
    private static final float FOOD_TO_BE_SATIATED_IN_KG = 0.15f;
    private static final int MAX_CREATURE_IN_CELL = 200;
    public Duck(String speciesName, int ageInDay,  Gender gender, float weightInKg,
                float satiety, DietType dietType) {
        super(speciesName, ageInDay, MAX_AGE_DAYS, gender, weightInKg, MAX_WEIGHT_IN_KG,
                CELL_PER_TURN, FOOD_TO_BE_SATIATED_IN_KG, satiety, dietType);
    }

    public  static int getMaxNumberOfAnimalInCell() {
        return MAX_CREATURE_IN_CELL;
    }
}

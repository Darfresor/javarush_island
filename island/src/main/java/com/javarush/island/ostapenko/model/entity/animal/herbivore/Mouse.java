package com.javarush.island.ostapenko.model.entity.animal.herbivore;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.Animal;

public class Mouse extends Animal {
    private static final int MAX_AGE_DAYS = 10 * 365;
    private static final float MAX_WEIGHT_IN_KG = 0.05f;
    private static final int MAX_CREATURE_IN_CELL = 500;
    private static final int CELL_PER_TURN = 1;
    private static final float FOOD_TO_BE_SATIATED_IN_KG = 0.01f;

    public Mouse(String speciesName, int age, Gender gender, float weightInKg,
                 float foodToBeFullySatiatedInKg,  DietType dietType) {
        super(speciesName, age, MAX_AGE_DAYS, gender, weightInKg,  MAX_WEIGHT_IN_KG, CELL_PER_TURN
                , foodToBeFullySatiatedInKg, FOOD_TO_BE_SATIATED_IN_KG, dietType);
    }

    @Override
    public  int getMaxNumberOfAnimalInCell() {
        return MAX_CREATURE_IN_CELL;
    }
    public static int getMaxNumberOfMouseInCell(){
        return MAX_CREATURE_IN_CELL;
    }
}

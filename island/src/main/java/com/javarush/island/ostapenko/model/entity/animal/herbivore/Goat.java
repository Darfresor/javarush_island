package com.javarush.island.ostapenko.model.entity.animal.herbivore;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.Animal;

public class Goat extends Animal {
    private static final int MAX_AGE_DAYS = 10 * 365;
    private static final float MAX_WEIGHT_IN_KG = 70;
    private static final int MAX_CREATURE_IN_CELL = 140;
    private static final int CELL_PER_TURN = 3;
    private static final float FOOD_TO_BE_SATIATED_IN_KG = 15;


    public Goat(String speciesName, int age, Gender gender, float weightInKg,
                float satiety, DietType dietType) {
        super(speciesName, age, MAX_AGE_DAYS, gender, weightInKg, MAX_WEIGHT_IN_KG, CELL_PER_TURN,
                FOOD_TO_BE_SATIATED_IN_KG, satiety, dietType);
    }

    @Override
    public  int getMaxNumberOfAnimalInCell() {
        return MAX_CREATURE_IN_CELL;
    }
    public static int getMaxNumberOfGoatInCell(){
        return MAX_CREATURE_IN_CELL;
    }

}


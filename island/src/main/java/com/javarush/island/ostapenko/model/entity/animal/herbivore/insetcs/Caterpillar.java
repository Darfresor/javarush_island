package com.javarush.island.ostapenko.model.entity.animal.herbivore.insetcs;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;

public class Caterpillar extends Insetcs{
    private static final int MAX_AGE_DAYS = 90;
    private static final float MAX_WEIGHT_IN_KG = 0.01f;
    private static final int CELL_PER_TURN = 0;
    private static final float FOOD_TO_BE_SATIATED_IN_KG = 0;
    private static final int MAX_CREATURE_IN_CELL = 3;

    public Caterpillar(String speciesName, int ageInDay,
            Gender gender, float weightInKg,
             float satiety, DietType dietType) {
        super(speciesName, ageInDay, MAX_AGE_DAYS, gender, weightInKg, MAX_WEIGHT_IN_KG,
                CELL_PER_TURN, FOOD_TO_BE_SATIATED_IN_KG, satiety, dietType, MAX_CREATURE_IN_CELL);
    }

    @Override
    public  int getMaxNumberOfAnimalInCell() {
        return MAX_CREATURE_IN_CELL;
    }
    public static int getMaxNumberOfCaterpillarInCell(){
        return MAX_CREATURE_IN_CELL;
    }
}

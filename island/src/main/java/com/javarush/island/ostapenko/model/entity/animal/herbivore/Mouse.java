package com.javarush.island.ostapenko.model.entity.animal.herbivore;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.Animal;

public class Mouse extends Animal {
    private static final int MAX_CREATURE_IN_CELL = 150;

    public Mouse(String speciesName, int age, int maxAge, Gender gender, float weightInKg, float maxWeightInKg, int cellsPerTurnSpeed,
                 float foodToBeFullySatiatedInKg, float satiety, DietType dietType, int maxNumberOfAnimalInCell) {
        super(speciesName, age, maxAge, gender, weightInKg, maxWeightInKg, cellsPerTurnSpeed, foodToBeFullySatiatedInKg, satiety, dietType);
    }

    public static int getMaxNumberOfAnimalInCell() {
        return MAX_CREATURE_IN_CELL;
    }
}

package com.javarush.island.ostapenko.model.entity.animal.predator;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.Animal;

public class Wolf extends Animal {
    public Wolf(String speciesName, int age, int maxAge, Gender gender, float weightInKg, float maxWeightInKg,
                int cellsPerTurnSpeed, float foodToBeFullySatiatedInKg, float satiety, DietType dietType) {
        super(speciesName, age, maxAge, gender, weightInKg, maxWeightInKg, cellsPerTurnSpeed, foodToBeFullySatiatedInKg, satiety, dietType);
    }

}

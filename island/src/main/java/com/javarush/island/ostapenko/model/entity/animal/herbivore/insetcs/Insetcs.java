package com.javarush.island.ostapenko.model.entity.animal.herbivore.insetcs;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.Animal;

public abstract class Insetcs extends Animal {
    public Insetcs(String speciesName, int ageInDay, int maxAgeInDay, Gender gender
            , float weightInKg, float maxWeightInKg, int cellsPerTurnSpeed, float foodToBeFullySatiatedInKg
            , float satiety, DietType dietType, int maxNumberOfAnimalInCell) {
        super(speciesName, ageInDay, maxAgeInDay, gender, weightInKg, maxWeightInKg
                ,cellsPerTurnSpeed, foodToBeFullySatiatedInKg, satiety, dietType);
    }
}

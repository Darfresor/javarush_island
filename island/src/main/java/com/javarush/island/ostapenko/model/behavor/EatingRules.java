package com.javarush.island.ostapenko.model.behavor;

import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Mouse;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;

import java.util.Map;

public class EatingRules {
    private static final Map<Class<? extends Creature>, Map<Class<? extends Creature>, Double>> RULES = Map.of(
            Wolf.class, Map.of(
                    Rabbit.class, 0.6,
                    Mouse.class, 0.8
            ),
            Rabbit.class, Map.of(
                    Dandelion.class,1.0
            )
    );
    public static boolean canEat(Class<? extends Creature> eater,Class<? extends Creature> target){

        return RULES.containsKey(eater) && RULES.get(eater).containsKey(target);
    }

    public static double getEatProbability(Class<? extends Creature> eater,Class<? extends Creature> target){

        return RULES.getOrDefault(eater, Map.of())
                .getOrDefault(target, 0.0);
    }
}

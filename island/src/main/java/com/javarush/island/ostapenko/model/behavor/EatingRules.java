package com.javarush.island.ostapenko.model.behavor;

import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.*;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Boar;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Duck;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Mouse;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.insetcs.Caterpillar;
import com.javarush.island.ostapenko.model.entity.animal.predator.*;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;

import java.util.Map;

public class EatingRules {
    private static final Map<Class<? extends Creature>, Map<Class<? extends Creature>, Double>> RULES = Map.of(
            Wolf.class, Map.of(
                    Horse.class, 0.1,
                    Deer.class, 0.15,
                    Rabbit.class, 0.6,
                    Mouse.class, 0.8,
                    Goat.class, 0.6,
                    Sheep.class, 0.7,
                    Boar.class, 0.15,
                    Buffalo.class,0.1,
                    Duck.class, 0.4

            ),
            Boa.class, Map.of(
                    Fox.class, 0.15,
                    Rabbit.class, 0.2,
                    Mouse.class, 0.4,
                    Duck.class, 0.1
            ),
            Fox.class, Map.of(
                    Rabbit.class, 0.7,
                    Mouse.class, 0.9,
                    Duck.class, 0.6,
                    Caterpillar.class,0.4
            ),
            Bear.class, Map.of(
                    Boa.class, 0.8,
                    Horse.class, 0.4,
                    Deer.class, 0.8,
                    Rabbit.class, 0.8,
                    Mouse.class, 0.9,
                    Goat.class, 0.7,
                    Sheep.class, 0.7,
                    Boar.class, 0.5,
                    Buffalo.class,0.2,
                    Duck.class, 0.1
            ),
            Eagle.class, Map.of(
                    Fox.class, 0.1,
                    Rabbit.class, 0.9,
                    Mouse.class, 0.9,
                    Duck.class, 0.8
            ),
            Horse.class, Map.of(
                    Dandelion.class,1.0
            ),
            Deer.class, Map.of(
                    Dandelion.class,1.0
            ),
            Rabbit.class, Map.of(
                    Dandelion.class,1.0
            ),

            Duck.class,Map.of(
                    Dandelion.class,1.0,
                    Caterpillar.class,0.9
            ),
            Caterpillar.class, Map.of(
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

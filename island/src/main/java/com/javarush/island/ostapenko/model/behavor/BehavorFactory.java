package com.javarush.island.ostapenko.model.behavor;

import com.javarush.island.ostapenko.model.behavor.implementaions.nerbivore.RabbitEatStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.nerbivore.RabbitMoveStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.nerbivore.RabbitReprocudeStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.predator.WolfEatStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.predator.WolfMoveStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.predator.WolfReproduceStrategy;
import com.javarush.island.ostapenko.model.behavor.interfaces.Breedable;
import com.javarush.island.ostapenko.model.behavor.interfaces.Eatable;
import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;

public class BehavorFactory {
    public static Moveable createMoveStrategy(Animal animal){
     return switch(animal){
       case Wolf w -> new WolfMoveStrategy();
       case Rabbit r -> new RabbitMoveStrategy();
       case null -> throw new RuntimeException("Animal cannot be null");
         default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
     };
    }

    public static Eatable createEatStrategy(Animal animal){
        return switch(animal){
            case Wolf w -> new WolfEatStrategy();
            case Rabbit r -> new RabbitEatStrategy();
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
        };
    }
    public static Breedable createReproduceStrategy(Animal animal){
        return switch(animal){
            case Wolf w -> new WolfReproduceStrategy();
            case Rabbit r -> new RabbitReprocudeStrategy();
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
        };
    }
}

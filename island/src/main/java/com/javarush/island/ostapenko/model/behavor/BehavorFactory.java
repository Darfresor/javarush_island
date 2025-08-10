package com.javarush.island.ostapenko.model.behavor;

import com.javarush.island.ostapenko.model.behavor.implementaions.NoOpEdibleStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.nerbivore.*;
import com.javarush.island.ostapenko.model.behavor.implementaions.predator.*;
import com.javarush.island.ostapenko.model.behavor.interfaces.*;
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
    public static Edible createBeingEatenStrategy(Animal animal){
        return switch(animal){
            case Wolf w -> new NoOpEdibleStrategy();
            case Rabbit r -> new RabbitBeingEatenStrategy();
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
        };
    }
    public static Starvable createStarvableStrategy(Animal animal){
        return switch(animal){
            case Wolf w -> new WolfStarvableStrategy();
            case Rabbit r -> new RabbitStarvableStrategy();
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
        };
    }
    public static Aging createAgingStrategy(Animal animal){
        return switch(animal){
            case Wolf w -> new WolfAgingStrategy();
            case Rabbit r -> new RabbitAgingStrategy();
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
        };
    }
}

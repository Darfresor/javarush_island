package com.javarush.island.ostapenko.model.behavor;

import com.javarush.island.ostapenko.model.behavor.implementaions.NoOpEdibleStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.GenericAnimalAgingStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore.*;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator.*;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.DandelionBeingEatenStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.DandelionReproduceStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.GenericPlantAgingStrategy;
import com.javarush.island.ostapenko.model.behavor.interfaces.*;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;
import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;

public class BehavorFactory {
    private BehavorFactory() {
    }

    public static Moveable createMoveStrategy(Animal animal){
     return switch(animal){
       case Wolf w -> new WolfMoveStrategy();
       case Rabbit r -> new RabbitMoveStrategy();
       case null -> throw new RuntimeException("Animal cannot be null");
       default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
     };
    }

    public static Eatable createEatStrategy(Animal animal, IMediator mediator){
        return switch(animal){
            case Wolf w -> new WolfEatStrategy(mediator);
            case Rabbit r -> new RabbitEatStrategy(mediator);
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
        };
    }
    public static AnimalReproducible createReproduceStrategy(Animal animal,IMediator mediator){
        return switch(animal){
            case Wolf w -> new WolfReproduceStrategy(mediator);
            case Rabbit r -> new RabbitReprocudeStrategy(mediator);
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + animal.getClass());
        };
    }
    public static PlantReproducible createReproduceStrategy(Plant plant, IMediator mediator){
        return switch(plant){
            case Dandelion d -> new DandelionReproduceStrategy();
            case null -> throw new RuntimeException("Plant cannot be null");
            default -> throw new RuntimeException("Unknown plant: " + plant.getClass());
        };
    }
    public static Edible<? extends Creature, ? extends Creature> createBeingEatenStrategy(Creature creature){
        return switch(creature){
            case Wolf w -> new NoOpEdibleStrategy();
            case Rabbit r -> new RabbitBeingEatenStrategy();
            case Dandelion d-> new DandelionBeingEatenStrategy();
            case null -> throw new RuntimeException("Creature cannot be null");
            default -> throw new RuntimeException("Unknown creature: " + creature.getClass());
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

    public static Aging<? extends Creature> createAgingStrategy(Creature creature){
        return  switch(creature){
            case Animal a -> new GenericAnimalAgingStrategy<>();
            case Plant p -> new GenericPlantAgingStrategy<>();
            case null -> throw new RuntimeException("creature cannot be null");
            default -> throw new RuntimeException("Unknown creature: " + creature.getClass());
        };
    }

}

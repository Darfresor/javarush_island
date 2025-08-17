package com.javarush.island.ostapenko.model.behavor;

import com.javarush.island.ostapenko.model.behavor.implementaions.NoOpEdibleStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.GenericAnimalAgingStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.GenericAnimalMoveStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.GenericAnimalReproduceStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.GenericAnimalStarvableStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore.*;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator.*;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.DandelionBeingEatenStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.GenericPlantAgingStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.GenericPlantReproduceStrategy;
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

    public static Moveable createMoveStrategy(Creature creature){
     return switch(creature){
       case Animal a -> new GenericAnimalMoveStrategy();
       case null -> throw new RuntimeException("Animal cannot be null");
       default -> throw new RuntimeException("Unknown animal: " + creature.getClass());
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
            case null -> throw new RuntimeException("Creature cannot be null");
            default -> new GenericAnimalReproduceStrategy(mediator);
        };
    }
    public static PlantReproducible createReproduceStrategy(Plant plant, IMediator mediator){
        return switch(plant){
            case null -> throw new RuntimeException("Plant cannot be null");
            default -> new GenericPlantReproduceStrategy();
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
    public static Starvable createStarvableStrategy(Creature creature){
        return switch(creature){
            case Animal w -> new GenericAnimalStarvableStrategy();
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + creature.getClass());
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

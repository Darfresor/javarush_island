package com.javarush.island.ostapenko.model.behavor;

import com.javarush.island.ostapenko.model.behavor.implementaions.GenericBeingEatenStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.animal.*;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.GenericPlantAgingStrategy;
import com.javarush.island.ostapenko.model.behavor.implementaions.plant.GenericPlantReproduceStrategy;
import com.javarush.island.ostapenko.model.behavor.interfaces.*;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.entity.plant.Plant;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;

public class BehavorFactory {
    private BehavorFactory() {
    }

    public static Moveable createMoveStrategy(Creature creature, IMediator mediator){
     return switch(creature){
       case Animal a -> new GenericAnimalMoveStrategy(mediator);
       case null -> throw new RuntimeException("Animal cannot be null");
       default -> throw new RuntimeException("Unknown animal: " + creature.getClass());
     };
    }

    public static Eatable createEatStrategy(Creature creature, IMediator mediator){
        return switch(creature){
            case Animal a -> switch(a.getDietType()){
                case CARNIVORE -> new GenericCarnivoreEatStrategy(mediator);
                case HERBIVORE -> new GenericHerbivoreStrategy(mediator);
                case OMNIVORE -> throw new RuntimeException("OMNIVORE strategy not exists ");
            };
            case null -> throw new RuntimeException("Creature cannot be null");
            default -> throw new RuntimeException("Unknown creature: " + creature.getClass());
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
            case null -> throw new RuntimeException("Creature cannot be null");
            default -> new GenericBeingEatenStrategy();
        };
    }

    public static Starvable createStarvableStrategy(Creature creature){
        return switch(creature){
            case Animal w -> new GenericAnimalStarvableStrategy();
            case null -> throw new RuntimeException("Animal cannot be null");
            default -> throw new RuntimeException("Unknown animal: " + creature.getClass());
        };
    }

    public static Aging<? extends Creature> createAgingStrategy(Creature creature, IMediator mediator){
        return  switch(creature){
            case Animal a -> new GenericAnimalAgingStrategy<>(mediator);
            case Plant p -> new GenericPlantAgingStrategy<>(mediator);
            case null -> throw new RuntimeException("creature cannot be null");
            default -> throw new RuntimeException("Unknown creature: " + creature.getClass());
        };
    }

}

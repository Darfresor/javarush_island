package com.javarush.island.ostapenko.model.services.behavor;

import com.javarush.island.ostapenko.model.behavor.BehavorFactory;
import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.mediator.IEventHandler;
import com.javarush.island.ostapenko.model.services.mediator.event.AnimalMoveEvent;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;

public class MovementService implements IEventHandler {
    public void executeMove(Animal animal, Cell currentCell, Island island) {
            Moveable strategy = BehavorFactory.createMoveStrategy(animal);
            strategy.move(animal, currentCell, island);

    }

    @Override
    public void handle(Event event) {
        switch(event){
            case AnimalMoveEvent e-> executeMove(e.getAnimal(), e.getCurrentCell(), e.getIsland());
            case null -> throw new RuntimeException("Event cannot be null");
            default -> throw new RuntimeException("Unknown event: " + event.getClass());
        }
    }
}

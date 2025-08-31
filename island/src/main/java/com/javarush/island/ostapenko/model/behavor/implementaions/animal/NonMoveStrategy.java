package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.core.util.Logger;
import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;

public class NonMoveStrategy implements Moveable {
    @Override
    public void move(Animal animal, Cell currentCell, Island island, Event event, ModelThreadPoolManager modelThreadPoolManager) {
        Logger.logMovementService(animal, currentCell, String.format("Для %s передвижение не предусмотрено правилами симуляции",
                animal.getSpeciesName()));
    }
}

package com.javarush.island.ostapenko.model.behavor.implementaions.animal;

import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;
import com.javarush.island.ostapenko.model.services.mediator.IMediator;
import com.javarush.island.ostapenko.model.services.mediator.event.*;
import com.javarush.island.ostapenko.core.util.Logger;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GenericAnimalMoveStrategy implements Moveable {
    private record Point(int x, int y) {
    }

    private final IMediator mediator;

    public GenericAnimalMoveStrategy(IMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void move(Animal animal, Cell currentCell, Island island, Event event, ModelThreadPoolManager modelThreadPoolManager) {
        if (animal.getCellsLeftInCurrentTurn() == 0) {
            Logger.logMovementService(animal, currentCell, String.format("%s устал и больше не может двигаться", animal.getSpeciesName()));
        } else {
            Logger.logMovementService(animal, currentCell, String.format("У %s осталось ходов %d",
                    animal.getSpeciesName(), animal.getCellsLeftInCurrentTurn()));
            animal.setCellsLeftInCurrentTurn(animal.getCellsLeftInCurrentTurn() - 1);
            Cell futureCell = chooseCellForMove(currentCell, island, animal);
            int countAnimalInFutureCell = countAnimalInCell(animal, futureCell);
            if (countAnimalInFutureCell >= animal.getMaxNumberOfAnimalInCell()) {
                Logger.logMovementService(animal, currentCell,
                        String.format("%s потратил силы но не смог попасть в соседнюю клетку из-за переполнения, у него осталось ходов %d",
                                animal.getSpeciesName(), animal.getCellsLeftInCurrentTurn()));
            } else {
                Cell originalCurrentCell = island.getCell(currentCell.getX(), currentCell.getY());
                originalCurrentCell.removeAnimal(animal);
                Cell originalFutureCell = island.getCell(futureCell.getX(), futureCell.getY());
                originalFutureCell.addAnimal(animal);
                Logger.logMovementService(animal, currentCell, String.format("%s передвинулся в соседнюю клетку, у него осталось ходов %d",
                        animal.getSpeciesName(), animal.getCellsLeftInCurrentTurn()));
                switch (event) {
                    case AnimalMoveForEatEvent e ->
                            modelThreadPoolManager.executeFeedTask(() -> mediator.notify(new AnimalEatEvent(animal, futureCell, island)));
                    case AnimalMoveForReproduceEvent e->
                            modelThreadPoolManager.executeReproduceTask(() -> mediator.notify(new AnimalCanReproduce(animal, futureCell, island)));
                    case null -> throw new RuntimeException("Event cannot be null");
                    default -> throw new RuntimeException("Unknown event: " + event.getClass());
                }
                ;
            }
        }

    }


    private Cell chooseCellForMove(Cell currentCell, Island island, Animal animal) {
        Map<String, Point> map = determineСellsForMovement(currentCell, island, animal);
        List<Map.Entry<String, Point>> list = new ArrayList<>(map.entrySet());
        if (list.isEmpty()) {
            Logger.logMovementService(animal, currentCell, String.format("%s некуда двигаться",
                    animal.getSpeciesName()));
            return null;
        } else {
            int directionOfMovement = ThreadLocalRandom.current().nextInt(0, list.size());
            String directionName = list.get(directionOfMovement).getKey();
            Point point = list.get(directionOfMovement).getValue();
            Logger.logMovementService(animal, currentCell, String.format("%s выбрал направление движения = %s, координаты движения = %s",
                    animal.getSpeciesName(), directionName, list.get(directionOfMovement).getValue()));
            int x = point.x();
            int y = point.y();
            return island.getGridCopy()[x][y];
        }
    }


    private Map<String, Point> determineСellsForMovement(Cell currentCell, Island island, Animal animal) {
        int maxX = island.getGridCopy().length;
        int maxY = island.getGridCopy()[0].length;
        Logger.logMovementService(animal, currentCell, String.format("Определяем направление движения для %s,Высота острова = %d клеток, а длина =%d ",
                animal.getSpeciesName(), maxX, maxY));
        Map<String, Point> map = new HashMap<>();
        if (currentCell.getY() - 1 >= 0) {
            map.put("left", new Point(currentCell.getX(), currentCell.getY() - 1));
        }
        if (currentCell.getY() + 1 < maxY) {
            map.put("right", new Point(currentCell.getX(), currentCell.getY() + 1));
        }
        if (currentCell.getX() - 1 >= 0) {
            map.put("top", new Point(currentCell.getX() - 1, currentCell.getY()));
        }
        if (currentCell.getX() + 1 < maxX) {
            map.put("bottom", new Point(currentCell.getX() + 1, currentCell.getY()));
        }
        return map;
    }

    private int countAnimalInCell(Animal animal, Cell cell) {
        int countAnimal = 0;
        for (UUID animalId : cell.getAnimalIds()) {
            Animal cellAnimal = cell.getAnimalById(animalId);
            if (cellAnimal.getClass() == animal.getClass()
            ) {
                countAnimal++;
            }
        }
        return countAnimal;
    }
}

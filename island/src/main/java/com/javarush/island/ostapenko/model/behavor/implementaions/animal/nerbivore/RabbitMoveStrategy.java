package com.javarush.island.ostapenko.model.behavor.implementaions.animal.nerbivore;

import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RabbitMoveStrategy implements Moveable {
    private record Point(int x, int y){};

    @Override
    public void move(Animal animal, Cell currentCell, Island island) {
        if (animal.getCellsLeftInCurrentTurn() == 0) {
            System.out.println("Кролик устал и больше не может двигаться");
        } else {
            System.out.println("У Кролик осталось ходов " + animal.getCellsLeftInCurrentTurn());
            animal.setCellsLeftInCurrentTurn(animal.getCellsLeftInCurrentTurn() - 1);
            Cell futureCell = chooseCellForMove(currentCell, island);

            Cell originalCurrentCell = island.getCell(currentCell.getX(), currentCell.getY());
            originalCurrentCell.removeAnimal(animal);

            Cell originalFutureCell = island.getCell(futureCell.getX(), futureCell.getY());
            originalFutureCell.addAnimal(animal);
            System.out.println("Кролик передвинулся в соседнюю клетку");
            System.out.println("У кролика осталось ходов " + animal.getCellsLeftInCurrentTurn());
        }

    }


    private Cell chooseCellForMove(Cell currentCell, Island island) {
        Map<String, Point> map = determineСellsForMovement(currentCell, island);
        List<Map.Entry<String, Point>> list = new ArrayList<>(map.entrySet());
        if (list.isEmpty()) {
            System.out.println("Кролику некуда двигаться");
            return null;
        } else {
            int directionOfMovement = ThreadLocalRandom.current().nextInt(0, list.size());
            String directionName = list.get(directionOfMovement).getKey();
            Point point = list.get(directionOfMovement).getValue();
            System.out.println("Кролик выбрал направление движения = "+ directionName);
            System.out.println(list.get(directionOfMovement).getValue());
            int x = point.x();
            int y = point.y();
            return island.getGridCopy()[x][y];
        }
    }



    private Map<String, Point> determineСellsForMovement(Cell currentCell, Island island) {
        int maxX = island.getGridCopy().length;
        int maxY = island.getGridCopy()[0].length;
        System.out.println(String.format("Высота острова = %d клеток, а длина =%d ", maxX, maxY));
        Map<String, Point> map = new HashMap<>();
        if (currentCell.getY() - 1 >= 0) {
            map.put("left", new Point(currentCell.getX(), currentCell.getY() - 1));
        }
        if (currentCell.getY() + 1 < maxY) {
            map.put("right", new Point(currentCell.getX(), currentCell.getY() + 1));
        }
        if (currentCell.getX() - 1 >= 0) {
            map.put("top", new Point(currentCell.getX()-1, currentCell.getY() ));
        }
        if (currentCell.getX() + 1 < maxX) {
            map.put("bottom", new Point(currentCell.getX()+1, currentCell.getY()));
        }
        return map;
    }
}

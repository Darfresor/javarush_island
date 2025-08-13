package com.javarush.island.ostapenko.model.behavor.implementaions.animal.predator;

import com.javarush.island.ostapenko.model.behavor.interfaces.Moveable;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WolfMoveStrategy implements Moveable {
    private record Point(int x, int y){};

    @Override
    public void move(Animal animal, Cell currentCell, Island island) {
        if (animal.getCellsLeftInCurrentTurn() == 0) {
            System.out.println("Волк устал и больше не может двигаться");
        } else {
            System.out.println("У волка осталось ходов " + animal.getCellsPerTurnSpeed());
            animal.setCellsLeftInCurrentTurn(animal.getCellsPerTurnSpeed() - 1);
            Cell futureCell = chooseCellForMove(currentCell, island);
            //currentCell.removeAnimal(animal);
            //futureCell.addAnimal(animal);
            System.out.println("Волк передвинулся в соседнюю клетку");
            System.out.println("У волка осталось ходов " + animal.getCellsLeftInCurrentTurn());
        }

    }


    private Cell chooseCellForMove(Cell currentCell, Island island) {
        Map<String, Point> map = determineСellsForMovement(currentCell, island);
        List<Map.Entry<String, Point>> list = new ArrayList<>(map.entrySet());
        if (list.isEmpty()) {
            System.out.println("Волку некуда двигаться");
            return null;
        } else {
            int directionOfMovement = ThreadLocalRandom.current().nextInt(0, list.size());
            String directionName = list.get(directionOfMovement).getKey();
            Point point = list.get(directionOfMovement).getValue();
            System.out.println("Волк выбрал направление движения = "+ directionName);
            System.out.println(list.get(directionOfMovement).getValue());
           int x = point.x();
           int y = point.y();
           return island.getCells()[x][y];
        }
    }



    private Map<String, Point> determineСellsForMovement(Cell currentCell, Island island) {
        int maxX = island.getCells().length;
        int maxY = island.getCells()[0].length;
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

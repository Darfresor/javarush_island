package com.javarush.island.ostapenko;


import com.javarush.island.ostapenko.app.Application;

/**
 * Entry Point of Application.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(Application.class, args);
    }
}

//TODO попробовать дополнительно классы зверей описать с помощью lombok, чтобы не писать каждый раз гетеры,сетеры и конструктор?
//TODO сделать управление настройка через сериализацию и десериализацию yaml?

//TODO сделать таблицу в интерфейсе, чтобы можно было управлять и изменять статистику пожирания
//TODO сделать отдельно интерфейс, возможно в виде таблицы? Где можно отдельно управлять конфигурацией + она должна мочь сохраняться для следующего запуска

//TODO фабрика для создания разных типов животных
/*
public interface AnimalFactory {
    Animal createAnimal(Cell cell);
}
----------------
// Для волков
public class WolfFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(Cell cell) {
        return new Wolf(cell, 50, 10); // health=50, speed=10
    }
}

// Для кроликов
public class RabbitFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(Cell cell) {
        return new Rabbit(cell, 30, 15);
    }
}
----------------
public class IslandSimulation {
    private Map<String, AnimalFactory> factories = new HashMap<>();

    public IslandSimulation() {
        // Регистрируем фабрики
        factories.put("wolf", new WolfFactory());
        factories.put("rabbit", new RabbitFactory());
    }

    public void spawnAnimal(String type, Cell cell) {
        Animal animal = factories.get(type).createAnimal(cell);
        cell.addAnimal(animal);
    }
}
* */
//TODO абстрактная фабрика если создаем например через разные климатические зоны и относящиеся к ним животные и растения
//TODO паттер прототип при размножении?
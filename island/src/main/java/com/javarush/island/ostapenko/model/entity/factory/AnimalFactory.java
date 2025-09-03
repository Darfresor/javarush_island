package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.core.exception.ApplicationException;
import com.javarush.island.ostapenko.model.entity.animal.Animal;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Deer;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Goat;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Horse;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Boar;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Mouse;
import com.javarush.island.ostapenko.model.entity.animal.omnivore.Duck;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.insetcs.Caterpillar;
import com.javarush.island.ostapenko.model.entity.animal.predator.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnimalFactory {
    private static final Map<Class<? extends Animal>, IAnimalCreator<? extends Animal>> creators = new HashMap<>();

    static {
        registerCreators();
    }

    private static void registerCreators() {
        creators.put(Wolf.class, AnimalFactory::createWolf);
        creators.put(Rabbit.class, AnimalFactory::createRabbit);
        creators.put(Duck.class, AnimalFactory::createDuck);
        creators.put(Caterpillar.class, AnimalFactory::createCaterpillar);
    }

    public static <T extends Animal> T createAnimal(Class<T> animalType) {
        IAnimalCreator<? extends Animal> creator = creators.get(animalType);
        if (creator == null) {
            throw new ApplicationException("Unknown animal type" + animalType);
        }
        return animalType.cast(creator.create());
    }

    private AnimalFactory() {
    }

    public static Wolf createWolf(Gender gender) {
        return new Wolf("Волк", (int) (Math.random() * 365 * 10) + 1,
                gender, 50f,
                1.0f, DietType.CARNIVORE);
    }

    public static Wolf createWolf(int age) {
        return new Wolf("Волк", age,
                Gender.values()[(int) Math.round(Math.random())], 50f,
                1.0f, DietType.CARNIVORE);
    }

    public static Wolf createWolf() {
        return new Wolf("Волк", (int) (Math.random() * 365 * 10) + 1,
                Gender.values()[(int) Math.round(Math.random())], 50f,
                1.0f, DietType.CARNIVORE);
    }

    public static List<Wolf> createWolfs(int countAnimal) {
        List<Wolf> wolfList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            wolfList.add(createWolf());
        }
        return wolfList;

    }

    public static Rabbit createRabbit(Gender gender) {
        return new Rabbit("Кролик", (int) (Math.random() * 365 * 10) + 1,
                gender, 2f,
                1.0f, DietType.HERBIVORE);
    }

    public static Rabbit createRabbit(int age) {
        return new Rabbit("Кролик", age,
                Gender.values()[(int) Math.round(Math.random())], 2f,
                1.0f, DietType.HERBIVORE);
    }

    public static Rabbit createRabbit() {
        return new Rabbit("Кролик", (int) (Math.random() * 365 * 10) + 1,
                Gender.values()[(int) Math.round(Math.random())], 2f,
                1.0f, DietType.HERBIVORE);
    }
    public static List<Rabbit> createRabbits(int countAnimal) {
        List<Rabbit> rabbitList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            rabbitList.add(createRabbit());
        }
        return rabbitList;
    }

    public static Duck createDuck() {
        return new Duck("Утка", (int) (Math.random() * 365 * 10) + 1,
                Gender.values()[(int) Math.round(Math.random())], 1f,
                1.0f, DietType.OMNIVORE);
    }
    public static List<Duck> createDucks(int countAnimal) {
        List<Duck> duckList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            duckList.add(createDuck());
        }
        return duckList;
    }
    public static Caterpillar createCaterpillar() {
        return new Caterpillar("Гусеница", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.HERBIVORE);
    }
    public static List<Caterpillar> createCaterpillars(int countAnimal) {
        List<Caterpillar> caterpillarList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            caterpillarList.add(createCaterpillar());
        }
        return caterpillarList;
    }

    public static Mouse createMouse() {
        return new Mouse("Мышь", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.OMNIVORE);
    }
    public static List<Mouse> createMouses(int countAnimal) {
        List<Mouse> mouseList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            mouseList.add(createMouse());
        }
        return mouseList;
    }

    public static Boa createBoa() {
        return new Boa("Удав", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.CARNIVORE);
    }
    public static List<Boa> createBoas(int countAnimal) {
        List<Boa> boaList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            boaList.add(createBoa());
        }
        return boaList;
    }


    public static Boar createBoar() {
        return new Boar("Кабан", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.OMNIVORE);
    }
    public static List<Boar> createBoars(int countAnimal) {
        List<Boar> boarList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            boarList.add(createBoar());
        }
        return boarList;
    }

    public static Fox createFox() {
        return new Fox("Лиса", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.CARNIVORE);
    }
    public static List<Fox> createFoxs(int countAnimal) {
        List<Fox> foxList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            foxList.add(createFox());
        }
        return foxList;
    }

    public static Bear createBear() {
        return new Bear("Медведь", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.CARNIVORE);
    }
    public static List<Bear> createBears(int countAnimal) {
        List<Bear> bearList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            bearList.add(createBear());
        }
        return bearList;
    }
    public static Eagle createEagle() {
        return new Eagle("Орел", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.CARNIVORE);
    }
    public static List<Eagle> createEagles(int countAnimal) {
        List<Eagle> eagleList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            eagleList.add(createEagle());
        }
        return eagleList;
    }
    public static Horse createHorse() {
        return new Horse("Лошадь", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.HERBIVORE);
    }
    public static List<Horse> createHorses(int countAnimal) {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            horseList.add(createHorse());
        }
        return horseList;
    }
    public static Deer createDeer() {
        return new Deer("Олень", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.HERBIVORE);
    }
    public static List<Deer> createDeers(int countAnimal) {
        List<Deer> deerList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            deerList.add(createDeer());
        }
        return deerList;
    }
    public static Goat createGoat() {
        return new Goat("Овца", (int) (Math.random() * 90) + 1,
                Gender.values()[(int) Math.round(Math.random())], 0.01f,
                0.5f, DietType.HERBIVORE);
    }
    public static List<Goat> createGoats(int countAnimal) {
        List<Goat> goatList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            goatList.add(createGoat());
        }
        return goatList;
    }



}

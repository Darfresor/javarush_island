package com.javarush.island.ostapenko.model.entity.factory;

import com.javarush.island.ostapenko.constants.DietType;
import com.javarush.island.ostapenko.constants.Gender;
import com.javarush.island.ostapenko.model.entity.animal.herbivore.Rabbit;
import com.javarush.island.ostapenko.model.entity.animal.predator.Wolf;

import java.util.ArrayList;
import java.util.List;



public class AnimalFactory {
    private AnimalFactory() {
    }

    public static Wolf createWolf(Gender gender) {
        return new Wolf("Волк", (int) (Math.random() * 365*10) + 1,
                gender,  50f,
                  1.0f, DietType.CARNIVORE);
    }
    public static Wolf createWolf(int age) {
        return new Wolf("Волк", age,
                Gender.values()[(int) Math.round(Math.random())], 50f,
                  1.0f, DietType.CARNIVORE);
    }
    public static Wolf createWolf() {
        return new Wolf("Волк", (int) (Math.random() * 365*10) + 1,
                Gender.values()[(int) Math.round(Math.random())],  50f,
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
        return new Rabbit("Кролик", (int) (Math.random() * 365*10) + 1,
                gender,  2f,
                1.0f, DietType.HERBIVORE);
    }
    public static Rabbit createRabbit(int age) {
        return new Rabbit("Кролик", age,
                Gender.values()[(int) Math.round(Math.random())], 2f,
                1.0f, DietType.HERBIVORE);
    }
    public static Rabbit creatRabbit() {
        return new Rabbit("Кролик", (int) (Math.random() * 365*10) + 1,
                Gender.values()[(int) Math.round(Math.random())],  2f,
                1.0f, DietType.HERBIVORE);
    }


}

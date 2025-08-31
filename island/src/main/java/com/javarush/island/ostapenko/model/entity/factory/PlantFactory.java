package com.javarush.island.ostapenko.model.entity.factory;


import com.javarush.island.ostapenko.model.entity.plant.Dandelion;
import com.javarush.island.ostapenko.model.entity.plant.Plant;

import java.util.HashMap;
import java.util.Map;

public class PlantFactory {
    private static Map<Class<? extends Plant>,IPlantCreator<? extends Plant>> creators = new HashMap<>();

    static{
        registerCreators();
    }

    private static void registerCreators(){
        creators.put(Dandelion.class,PlantFactory::createDandelion);
    }
   public static <T extends  Plant> T createPlant(Class<T> plantType){
        IPlantCreator<? extends Plant> creator = creators.get(plantType);
        return plantType.cast(creator.create());
    }

    private PlantFactory() {
    }


    public static Dandelion createDandelion(int age) {
        return new Dandelion("Одуванчик",age,1f);
    }
    public static Dandelion createDandelion() {
        return new Dandelion("Одуванчик",(int) (Math.random() * 365*2) + 1,1f);
    }
}

package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;
import com.javarush.island.ostapenko.model.services.executors.ModelThreadPoolManager;

public interface Aging<T extends Creature>{

void deathDueToOldAge(T creature, Cell cell, Island island, ModelThreadPoolManager modelThreadPoolManager);
}

package com.javarush.island.ostapenko.model.behavor.interfaces;

import com.javarush.island.ostapenko.model.entity.Creature;
import com.javarush.island.ostapenko.model.island.Cell;
import com.javarush.island.ostapenko.model.island.Island;

public interface Aging<T extends Creature>{

void deathDueToOldAge(T creature, Cell cell, Island island);
}

package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.dto.CommandResponse;

public class StartSimulationCommand implements ICommand{
    @Override
    public CommandResponse execute() {
        System.out.println("Создана команда запускающая симуляцию острова");
        return null;
    }

}

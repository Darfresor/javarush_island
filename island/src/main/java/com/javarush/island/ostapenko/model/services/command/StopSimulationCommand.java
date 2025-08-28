package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.dto.CommandResponse;

public class StopSimulationCommand implements ICommand{
    @Override
    public CommandResponse execute() {
        System.out.println("Создана команды для остановки симуляции острова");
        return null;
    }

    @Override
    public CommandType getCommandType() {
        return null;
    }
}

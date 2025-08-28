package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.dto.CommandResponse;

public class UnsupportedCommand implements ICommand{
    @Override
    public CommandResponse execute() {
        System.out.println("Данная команда не поддерживается");
        return null;
    }

    @Override
    public CommandType getCommandType() {
        return null;
    }
}

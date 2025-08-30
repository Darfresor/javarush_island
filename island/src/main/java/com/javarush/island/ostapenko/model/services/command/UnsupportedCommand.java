package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.core.dto.CommandResponse;

public class UnsupportedCommand implements ICommand{
    @Override
    public CommandResponse execute() {
        System.out.println("Данная команда не поддерживается");
        return null;
    }

}

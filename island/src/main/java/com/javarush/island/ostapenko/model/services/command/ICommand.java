package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.dto.CommandResponse;

public interface ICommand {
    CommandResponse execute();
    CommandType getCommandType();
}

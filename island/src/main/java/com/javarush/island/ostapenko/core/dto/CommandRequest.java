package com.javarush.island.ostapenko.core.dto;

import com.javarush.island.ostapenko.constants.CommandType;

public class CommandRequest {
    private final CommandType commandType;
    private final Object commandData;

    public CommandRequest(CommandType commandType, Object commandData) {
        this.commandType = commandType;
        this.commandData = commandData;
    }

    public <T> T getData(Class<T> type) {
        return type.cast(commandData);
    }

    public CommandType getCommandType() {
        return commandType;
    }
}

package com.javarush.island.ostapenko.core.dto;

import com.javarush.island.ostapenko.constants.ResultCode;
import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.core.exception.ApplicationException;

public class CommandResponse {
    private ResultCode resultCode;
    private ApplicationException applicationException;
    private String message;
    private Object result;
    private CommandType commandType;

    public CommandResponse(CommandType commandType, Object result){
        this.commandType = commandType;
        this.result = result;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }

    public String getMessage() {
        return message;
    }

    public <T> T getResult(Class<T> type) {
        return type.cast(result);
    }

    public CommandType getCommandType() {
        return commandType;
    }
}

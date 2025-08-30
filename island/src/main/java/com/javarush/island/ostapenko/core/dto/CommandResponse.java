package com.javarush.island.ostapenko.core.dto;

import com.javarush.island.ostapenko.constants.ResultCode;
import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.core.exception.ApplicationException;

public class CommandResponse {
    private ResultCode resultCode;
    private ApplicationException applicationException;
    private String message;
    private Object Result;
    private CommandType commandType;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }

    public String getMessage() {
        return message;
    }

    public Object getResult() {
        return Result;
    }

    public CommandType getCommandType() {
        return commandType;
    }
}

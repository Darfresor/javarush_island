package com.javarush.island.ostapenko.core.dto;

import com.javarush.island.ostapenko.core.exception.ApplicationException;
import com.javarush.island.ostapenko.constants.ResultCode;

public class ModelResponse {
    private ResultCode resultCode;
    private ApplicationException applicationException;
}

package com.javarush.island.ostapenko.dto;

import com.javarush.island.ostapenko.exception.ApplicationException;
import com.javarush.island.ostapenko.constants.ResultCode;

public class Result {
    private ResultCode resultCode;
    private ApplicationException applicationException;
}

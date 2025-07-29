package com.javarush.island.ostapenko.model.entity;

import com.javarush.island.ostapenko.exception.ApplicationException;
import com.javarush.island.ostapenko.model.repository.ResultCode;

public class Result {
    private ResultCode resultCode;
    private ApplicationException applicationException;
}

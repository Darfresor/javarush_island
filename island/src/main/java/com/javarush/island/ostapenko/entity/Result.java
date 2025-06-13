package com.javarush.island.ostapenko.entity;

import com.javarush.island.ostapenko.exception.ApplicationException;
import com.javarush.island.ostapenko.repository.ResultCode;

public class Result {
    private ResultCode resultCode;
    private ApplicationException applicationException;
}

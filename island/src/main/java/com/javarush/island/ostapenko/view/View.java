package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.dto.Result;

public interface View {
    String [] getParametrs();
    void printResult(Result result);
}

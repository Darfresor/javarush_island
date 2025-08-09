package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.dto.ModelResponse;

public interface View {
    String [] getParametrs();
    void printResult(ModelResponse modelResponse);
}

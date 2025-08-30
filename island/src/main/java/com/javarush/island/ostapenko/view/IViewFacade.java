package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.core.dto.CommandRequest;
import com.javarush.island.ostapenko.core.dto.CommandResponse;

public interface IViewFacade {
    CommandRequest getParametrs();
    void printResult(CommandResponse commandResponse);
    void setupEventHandlers(Runnable runnable);
}

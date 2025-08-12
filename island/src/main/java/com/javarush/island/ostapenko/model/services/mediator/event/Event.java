package com.javarush.island.ostapenko.model.services.mediator.event;

import com.javarush.island.ostapenko.constants.EventType;

public interface Event {
    EventType getType();
}

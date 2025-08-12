package com.javarush.island.ostapenko.model.services.mediator;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;

public interface IMediator {
    void notify(Event event);
    void subsribe(EventType type, IEventHandler handler);
}

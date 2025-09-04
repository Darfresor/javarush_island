package com.javarush.island.ostapenko.model.services.mediator;

import com.javarush.island.ostapenko.constants.EventType;
import com.javarush.island.ostapenko.model.services.mediator.event.Event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EventMediator implements IMediator{
    private final Map<EventType, List<IEventHandler>> handlers = new ConcurrentHashMap<>();

    @Override
    public void subsribe(EventType type, IEventHandler handler) {
        handlers.computeIfAbsent(type, k -> new ArrayList<>()).add(handler);
    }

    @Override
    public void notify(Event event) {
        handlers.getOrDefault(event.getType(), Collections.emptyList())
                .forEach(h -> h.handle(event));
    }


}

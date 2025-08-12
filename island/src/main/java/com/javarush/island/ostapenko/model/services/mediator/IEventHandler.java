package com.javarush.island.ostapenko.model.services.mediator;

import com.javarush.island.ostapenko.model.services.mediator.event.Event;

/**
 * Название не Colleague, так как сервисы с которыми будем работать не являются инициаторами событий или полноправными "коллегами"
 */
public interface IEventHandler {
    void handle (Event event);
}

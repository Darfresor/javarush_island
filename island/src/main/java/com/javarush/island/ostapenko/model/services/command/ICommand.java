package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.core.dto.CommandResponse;

public interface ICommand {
    CommandResponse execute();
}

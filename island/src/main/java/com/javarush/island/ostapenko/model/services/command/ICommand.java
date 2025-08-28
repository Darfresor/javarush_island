package com.javarush.island.ostapenko.model.services.command;

import com.javarush.island.ostapenko.dto.CommandResponse;

public interface ICommand {
    CommandResponse execute();
}

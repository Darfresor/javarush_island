package com.javarush.island.ostapenko.core.dto;

public class ModelRequest {
    private final Object modelData;

    public ModelRequest(Object modelData) {
        this.modelData = modelData;
    }

    public <T> T getData(Class<T> type) {
        return type.cast(modelData);
    }
}

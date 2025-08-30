package com.javarush.island.ostapenko.core.observer;

public interface IStatisticObservable {
    void addStatisticListener(IStatisticObservable listener);
}

package com.stady.cars.domain.model;

public enum SendType {
    HTTP(1),
    ERROR(3),
    SMS(2) // ToDo Всегда uppercase
    ;

    SendType(int priority) {

    }
}

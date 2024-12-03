package com.stady.cars.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SendType {
    HTTP(1),
    ERROR(3),
    SMS(2); // ToDo Всегда uppercase
    private final Integer priority;

    SendType(Integer priority) {

        this.priority = priority;
    }
    public Integer getValue() {
        return this.priority;
    }
}

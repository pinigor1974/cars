package com.stady.cars.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SendType {
    HTTP("1"),
    ERROR("3"),
    SMS("2"); // ToDo Всегда uppercase
    private final String priority;

    SendType(String priority) {

        this.priority = priority;
    }
    @JsonValue
    public String getValue() {
        return this.priority;
    }
}

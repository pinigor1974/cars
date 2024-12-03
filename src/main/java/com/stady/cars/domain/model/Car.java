package com.stady.cars.domain.model;

import java.util.List;

public record Car(String name, SendType prioritizedSendType, List<SendType> sendTypes, List<CommandTypeEnum> codes) {
}

// Fixed ToDo что делать, если prioritizedSendType не задан?
// Fixed ToDO может быть просто список каналов отправки и нужно отправить на первый УДАЧНЫЙ

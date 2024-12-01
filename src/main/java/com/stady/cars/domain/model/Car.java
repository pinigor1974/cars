package com.stady.cars.domain.model;

import java.util.List;

public record Car(String name, String prioritizedSendType, List<SendType> sendTypes, List<String> codes) {
}

// ToDo что делать, если prioritizedSendType не задан?
// ToDO может быть просто список каналов отправки и нужно отправить на первый УДАЧНЫЙ

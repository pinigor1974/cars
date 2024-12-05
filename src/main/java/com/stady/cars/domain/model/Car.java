package com.stady.cars.domain.model;

import java.util.List;

public record Car(String name, String number, SendType prioritizedSendType, List<SendType> sendTypes,
                  List<CommandTypeEnum> codes) {
}

// ToDO прикрутить кастомный валидатор для number, чтобы проверять валидность с помощью регулярки

// Fixed ToDo что делать, если prioritizedSendType не задан?
// Fixed ToDO может быть просто список каналов отправки и нужно отправить на первый УДАЧНЫЙ

package pro.nevercute.cars.adapter.out.inMemory.domain;

import pro.nevercute.cars.domain.model.CommandTypeEnum;
import pro.nevercute.cars.domain.model.EngineStatus;
import pro.nevercute.cars.domain.model.SendType;
import lombok.Data;

import java.util.List;

// ToDO прикрутить кастомный валидатор для number, чтобы проверять валидность с помощью регулярки
// Fixed ToDo что делать, если prioritizedSendType не задан?
// Fixed ToDO может быть просто список каналов отправки и нужно отправить на первый УДАЧНЫЙ
@Data
public final class Car {
    private String name;
    private String number;
    private pro.nevercute.cars.domain.model.SendType prioritizedSendType;
    private List<SendType> sendTypes;
    private List<CommandTypeEnum> codes;
    private EngineStatus engineStatus;
}

package pro.nevercute.cars.adapter.out.inMemory.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pro.nevercute.cars.domain.model.CommandTypeEnum;
import pro.nevercute.cars.domain.model.EngineStatus;
import pro.nevercute.cars.domain.model.SendType;
import lombok.Data;

import java.util.List;

// ToDO прикрутить кастомный валидатор для number, чтобы проверять валидность с помощью регулярки
// Fixed ToDo что делать, если prioritizedSendType не задан?
// Fixed ToDO может быть просто список каналов отправки и нужно отправить на первый УДАЧНЫЙ
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Car {
    private String name;
    private String number;
    private SendType prioritizedSendType;
    private List<SendType> sendTypes;
    private List<CommandTypeEnum> codes;
    private EngineStatus engineStatus;
}

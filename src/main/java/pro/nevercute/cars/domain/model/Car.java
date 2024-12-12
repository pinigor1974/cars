package pro.nevercute.cars.domain.model;

import pro.nevercute.cars.domain.command.SwitchEngineOnCommand;
import pro.nevercute.cars.domain.event.CarEvent;
import pro.nevercute.cars.domain.event.EngineSwitchedOnCarEvent;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public final class Car {
    private String name;
    private String number;
    private SendType prioritizedSendType;
    private List<SendType> sendTypes;
    private List<CommandTypeEnum> codes;
    private EngineStatus engineStatus;

    public Car(String name, String number, SendType prioritizedSendType, List<SendType> sendTypes,
               List<CommandTypeEnum> codes) {
        this.name = name;
        this.number = number;
        this.prioritizedSendType = prioritizedSendType;
        this.sendTypes = sendTypes;
        this.codes = codes;
    }

    public CarEvent handle(SwitchEngineOnCommand command) {
        engineStatus = EngineStatus.ON;
        return new EngineSwitchedOnCarEvent(this.number);
    }

    public String name() {
        return name;
    }

    public String number() {
        return number;
    }

    public SendType prioritizedSendType() {
        return prioritizedSendType;
    }

    public List<SendType> sendTypes() {
        return sendTypes;
    }

    public List<CommandTypeEnum> codes() {
        return codes;
    }
}

// ToDO прикрутить кастомный валидатор для number, чтобы проверять валидность с помощью регулярки

// Fixed ToDo что делать, если prioritizedSendType не задан?
// Fixed ToDO может быть просто список каналов отправки и нужно отправить на первый УДАЧНЫЙ

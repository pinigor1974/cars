package pro.nevercute.cars.application.port.in.impl;

import pro.nevercute.cars.application.event.CarApplicationEventPublisher;
import pro.nevercute.cars.application.port.in.use_case.SwitchEngineOffUseCase;
import pro.nevercute.cars.application.service.SendService;
import pro.nevercute.cars.domain.command.SwitchEngineOnCommand;
import pro.nevercute.cars.domain.command.result.SwitchEngineOnResult;
import pro.nevercute.cars.domain.model.CommandTypeEnum;
import pro.nevercute.cars.domain.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static pro.nevercute.cars.domain.model.CommandTypeEnum.ENGINE_OFF;
import static pro.nevercute.cars.domain.model.CommandTypeEnum.ENGINE_ON;


@Component
@RequiredArgsConstructor
public class SwitchEngineOffUseCaseImpl implements SwitchEngineOffUseCase {

    private final CarRepository carRepository;
    private final SendService service;
    private final CarApplicationEventPublisher carApplicationEventPublisher;

    @Override
    public SwitchEngineOnResult putEngineOff(SwitchEngineOnCommand command) {
        var car = carRepository.findByModelNameAndNumber(command.modelName(), command.modelNumber())
                .orElseThrow(); //ToDo сделать норм исключения
        var sentByChannelResult = service.send(ENGINE_OFF, car);
        var event = car.handle(command);
        carRepository.save(car);
        carApplicationEventPublisher.publishEvent(event);
        return new SwitchEngineOnResult(car.getNumber(), ENGINE_OFF); //ToDo сделать норм результат, логичный
    }
}

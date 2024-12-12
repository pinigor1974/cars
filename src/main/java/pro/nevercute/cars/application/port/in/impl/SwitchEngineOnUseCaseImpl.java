package pro.nevercute.cars.application.port.in.impl;

import pro.nevercute.cars.application.event.CarApplicationEventPublisher;
import pro.nevercute.cars.application.port.in.use_case.SwitchEngineOnUseCase;
import pro.nevercute.cars.application.service.CarRepositoryService;
import pro.nevercute.cars.application.service.SendService;
import pro.nevercute.cars.domain.command.SwitchEngineOnCommand;
import pro.nevercute.cars.domain.command.result.SwitchEngineOnResult;
import pro.nevercute.cars.domain.model.CommandTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SwitchEngineOnUseCaseImpl implements SwitchEngineOnUseCase {

    private final CarRepositoryService carRepositoryService;
    private final SendService service;
    private final CarApplicationEventPublisher carApplicationEventPublisher;

    @Override
    public SwitchEngineOnResult putEngineOn(SwitchEngineOnCommand command) {
        var car = carRepositoryService.findByModelNameAndNumber(command.modelName(), command.modelNumber());
        var sentByChannelResult = service.send(CommandTypeEnum.ENGINE_ON, car);
        var event = car.handle(command);
        carRepositoryService.save(car);
        carApplicationEventPublisher.publishEvent(event);
        return new SwitchEngineOnResult(car.getNumber(), CommandTypeEnum.ENGINE_ON);
    }
}

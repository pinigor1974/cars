package com.stady.cars.application.port.in.impl;

import com.stady.cars.application.port.in.use_case.SwitchEngineOnUseCase;
import com.stady.cars.application.service.SendService;
import com.stady.cars.domain.command.SwitchEngineOnCommand;
import com.stady.cars.domain.command.result.SwitchEngineOnResult;
import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SwitchEngineOnUseCaseImpl implements SwitchEngineOnUseCase {

    private final CarRepository carRepository;
    private final SendService service;

    @Override
    public SwitchEngineOnResult putEngineOn(SwitchEngineOnCommand command) {
        var car = carRepository.findByModelNameAndNumber(command.modelName(), command.modelNumber())
                .orElseThrow(() -> {
                    throw new RuntimeException(
                            "Не удалось найти настройки машины для команды %s".formatted(command.toString())
                    );
                });
        var sentByChannelResult = service.send(CommandTypeEnum.ENGINE_ON, car);
        return new SwitchEngineOnResult(car.modelNumber(), sentByChannelResult);
    }
}

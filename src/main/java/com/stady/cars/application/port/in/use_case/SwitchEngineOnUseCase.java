package com.stady.cars.application.port.in.use_case;

import com.stady.cars.domain.command.SwitchEngineOnCommand;
import com.stady.cars.domain.command.result.SwitchEngineOnResult;
import com.stady.cars.domain.model.CommandTypeEnum;

public interface SwitchEngineOnUseCase {
    SwitchEngineOnResult putEngineOn(SwitchEngineOnCommand command);
}

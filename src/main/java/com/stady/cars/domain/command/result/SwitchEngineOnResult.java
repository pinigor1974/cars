package com.stady.cars.domain.command.result;

import com.stady.cars.domain.model.CommandTypeEnum;

public record SwitchEngineOnResult(String modelNumber, CommandTypeEnum triggeredCommandType) {
}

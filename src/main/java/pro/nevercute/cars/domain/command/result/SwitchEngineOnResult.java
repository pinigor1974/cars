package pro.nevercute.cars.domain.command.result;

import pro.nevercute.cars.domain.model.CommandTypeEnum;

public record SwitchEngineOnResult(String modelNumber, CommandTypeEnum triggeredCommandType) {
}

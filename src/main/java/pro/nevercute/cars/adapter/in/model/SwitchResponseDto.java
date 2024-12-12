package pro.nevercute.cars.adapter.in.model;

import pro.nevercute.cars.domain.model.CommandTypeEnum;

public record SwitchResponseDto(String modelNumber, CommandTypeEnum triggeredCommandType) {
}

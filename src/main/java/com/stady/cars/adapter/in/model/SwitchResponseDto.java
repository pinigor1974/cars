package com.stady.cars.adapter.in.model;

import com.stady.cars.domain.model.CommandTypeEnum;

public record SwitchResponseDto(String modelNumber, CommandTypeEnum triggeredCommandType) {
}

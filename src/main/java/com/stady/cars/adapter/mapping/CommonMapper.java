package com.stady.cars.adapter.mapping;

import com.stady.cars.adapter.in.model.SwitchRequestDto;
import com.stady.cars.adapter.in.model.SwitchResponseDto;
import com.stady.cars.domain.command.SwitchEngineOnCommand;
import com.stady.cars.domain.command.result.SwitchEngineOnResult;

// ToDo потом применить mapStruct библу
public class CommonMapper {
    private CommonMapper() {

    }

    public static SwitchEngineOnCommand mapRequestToCommand(SwitchRequestDto requestDto) {
        return new SwitchEngineOnCommand(requestDto.modelName(), requestDto.modelNumber());
    }

    public static SwitchResponseDto mapResultToResponseDto(SwitchEngineOnResult result) {
        return new SwitchResponseDto(result.modelNumber(), result.triggeredCommandType());
    }
}

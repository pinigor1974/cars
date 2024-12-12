package pro.nevercute.cars.adapter.mapping;

import pro.nevercute.cars.adapter.in.model.SwitchRequestDto;
import pro.nevercute.cars.adapter.in.model.SwitchResponseDto;
import pro.nevercute.cars.domain.command.SwitchEngineOnCommand;
import pro.nevercute.cars.domain.command.result.SwitchEngineOnResult;

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

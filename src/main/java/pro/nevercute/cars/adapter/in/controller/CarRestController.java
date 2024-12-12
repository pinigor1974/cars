package pro.nevercute.cars.adapter.in.controller;

import pro.nevercute.cars.adapter.in.controller.exception.SomeInternalErrorException;
import pro.nevercute.cars.adapter.in.model.SwitchRequestDto;
import pro.nevercute.cars.adapter.in.model.SwitchResponseDto;
import pro.nevercute.cars.application.port.in.use_case.SwitchEngineOnUseCase;
import pro.nevercute.cars.adapter.mapping.CommonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CarRestController {

    private final SwitchEngineOnUseCase switchEngineOnUseCase;

    @PostMapping(value = "/switch-engine-on", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public SwitchResponseDto switchEngineOn(@RequestBody SwitchRequestDto requestDto) {
        try {
            var result = switchEngineOnUseCase.putEngineOn(CommonMapper.mapRequestToCommand(requestDto));
            return  CommonMapper.mapResultToResponseDto(result);
        } catch (Exception e) {
            log.error("Произошла ошибка при попытке завести мотор для машины {}", requestDto);
            throw new SomeInternalErrorException(e.getMessage(), e.getCause());
        }
    }
}

package com.stady.cars.adapter.in.controller;

import com.stady.cars.adapter.in.controller.exception.SomeInternalErrorException;
import com.stady.cars.adapter.in.model.SwitchRequestDto;
import com.stady.cars.adapter.in.model.SwitchResponseDto;
import com.stady.cars.application.port.in.use_case.SwitchEngineOnUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.stady.cars.adapter.mapping.CommonMapper.mapRequestToCommand;
import static com.stady.cars.adapter.mapping.CommonMapper.mapResultToResponseDto;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CarRestController {

    private final SwitchEngineOnUseCase switchEngineOnUseCase;

    @PostMapping(value = "/switch-engine-on", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public SwitchResponseDto switchEngineOn(@RequestBody SwitchRequestDto requestDto) {
        try {
            var result = switchEngineOnUseCase.putEngineOn(mapRequestToCommand(requestDto));
            return  mapResultToResponseDto(result);
        } catch (Exception e) {
            log.error("Произошла ошибка при попытке завести мотор для машины {}", requestDto);
            throw new SomeInternalErrorException(e.getMessage(), e.getCause());
        }
    }
}

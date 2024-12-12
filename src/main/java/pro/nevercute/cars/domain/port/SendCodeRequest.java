package pro.nevercute.cars.domain.port;

import pro.nevercute.cars.domain.model.CommandTypeEnum;

public record SendCodeRequest(CommandTypeEnum code) {}

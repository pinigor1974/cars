package com.stady.cars.service;

import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.model.SendType;

public interface SendCodeService {
    CommandTypeEnum send(CommandTypeEnum code);
    Boolean supports(SendType sendType);

}

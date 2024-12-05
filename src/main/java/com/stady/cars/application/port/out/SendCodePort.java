package com.stady.cars.application.port.out;

import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.model.SendType;

public interface SendCodePort {
    CommandTypeEnum send(CommandTypeEnum code);
    Boolean supports(SendType sendType);

}

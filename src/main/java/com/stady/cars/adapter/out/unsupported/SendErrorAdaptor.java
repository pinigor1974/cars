package com.stady.cars.adapter.out.unsupported;

import com.stady.cars.application.port.out.SendCodePort;
import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.model.SendType;

public class SendErrorAdaptor implements SendCodePort {
    @Override
    public CommandTypeEnum send(CommandTypeEnum code) {
        throw new RuntimeException();
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.ERROR;
    }
}

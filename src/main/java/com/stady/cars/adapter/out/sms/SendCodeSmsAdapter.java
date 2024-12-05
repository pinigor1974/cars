package com.stady.cars.adapter.out.sms;

import com.stady.cars.application.port.out.SendCodePort;
import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.model.SendType;

public class SendCodeSmsAdapter implements SendCodePort {

    @Override
    public CommandTypeEnum send(CommandTypeEnum code) {
        return code;
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.SMS;
    }
}

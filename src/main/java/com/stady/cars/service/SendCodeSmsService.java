package com.stady.cars.service;

import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.model.SendType;

public class SendCodeSmsService implements SendCodeService{

    @Override
    public CommandTypeEnum send(CommandTypeEnum code) {
        return code;
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.SMS;
    }
}

package com.stady.cars.service;

import com.stady.cars.domain.model.Codes;
import com.stady.cars.domain.model.SendType;

public class SendCodeSmsService implements SendCodeService{

    @Override
    public Codes send(Codes code) {
        return code;
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.SMS;
    }
}

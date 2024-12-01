package com.stady.cars.service;

import com.stady.cars.data.Codes;
import com.stady.cars.data.SendType;

public class SendCodeSmsService implements SendCodeService{

    @Override
    public Codes send(Codes code) {
        return code;
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.sms;
    }
}

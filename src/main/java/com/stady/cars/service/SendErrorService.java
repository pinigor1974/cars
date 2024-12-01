package com.stady.cars.service;

import com.stady.cars.data.Codes;
import com.stady.cars.data.SendType;

public class SendErrorService implements SendCodeService{
    @Override
    public Codes send(Codes code) {
        throw new RuntimeException();
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.error;
    }
}

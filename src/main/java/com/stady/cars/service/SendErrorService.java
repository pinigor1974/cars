package com.stady.cars.service;

import com.stady.cars.domain.model.Codes;
import com.stady.cars.domain.model.SendType;

public class SendErrorService implements SendCodeService{
    @Override
    public Codes send(Codes code) {
        throw new RuntimeException();
    }

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.ERROR;
    }
}

package com.stady.cars.service;

import com.stady.cars.domain.model.Codes;
import com.stady.cars.domain.model.SendType;

public interface SendCodeService {
    Codes send(Codes code);
    Boolean supports(SendType sendType);

}

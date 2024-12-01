package com.stady.cars.service;

import com.stady.cars.data.Codes;
import com.stady.cars.data.SendType;

public interface SendCodeService {
    Codes send(Codes code);
    Boolean supports(SendType sendType);

}

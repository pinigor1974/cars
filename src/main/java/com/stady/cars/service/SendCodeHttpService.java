package com.stady.cars.service;

import com.stady.cars.domain.model.Codes;
import com.stady.cars.domain.model.SendType;

public class SendCodeHttpService implements SendCodeService{

    private RostelekomProvider rostelekomProvider; // ToDO в реализации какой-то restTemplate клиент

    @Override
    public Codes send(Codes code) {
        return rostelekomProvider.sendCode(code); // ToDo представим, что здесь мы хотим реально сходить во внешний сервис
    }

    // ToDo реализовать какой-то стаб сервис, который реально должен вызываться (в тестах, в реализации пусть остается
    //  какой-то интерфейс, который и вызывается. В тестах подключить wiremock и определить request|response и протестировать интеграцию

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.HTTP;
    }



}

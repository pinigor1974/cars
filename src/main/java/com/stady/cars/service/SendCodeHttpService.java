package com.stady.cars.service;

import com.stady.cars.configuration.CarApplicationConfiguration;
import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.model.SendType;
import com.stady.cars.domain.model.Services;

public class SendCodeHttpService implements SendCodeService{

    private RostelecomProvider rostelekomProvider;
    private CarApplicationConfiguration carApplicationConfiguration;
    public SendCodeHttpService(RostelecomProvider rostelecomProvider, CarApplicationConfiguration carApplicationConfiguration){
        this.rostelekomProvider = rostelecomProvider;
        this.carApplicationConfiguration = carApplicationConfiguration;
    }
// Fixed ToDO в реализации какой-то restTemplate клиент

    @Override
    public CommandTypeEnum send(CommandTypeEnum code) {
        try {
            return rostelekomProvider.send(carApplicationConfiguration.getServices().get(Services.ROSTELECOM), code); //Done ToDo представим, что здесь мы хотим реально сходить во внешний сервис
        }catch (Exception e){
            throw new RuntimeException("Error send " + code.name());
        }

    }

    //Fixed ToDo реализовать какой-то стаб сервис, который реально должен вызываться (в тестах, в реализации пусть остается
    //  какой-то интерфейс, который и вызывается. В тестах подключить wiremock и определить request|response и протестировать интеграцию

    @Override
    public Boolean supports(SendType sendType) {
        return sendType == SendType.HTTP;
    }



}

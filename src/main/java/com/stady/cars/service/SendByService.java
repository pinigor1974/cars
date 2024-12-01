package com.stady.cars.service;
import com.stady.cars.domain.model.Car;
import com.stady.cars.domain.model.Codes;
import com.stady.cars.domain.model.SendType;

import java.util.List;

public class SendByService  {
    private List<SendCodeService> sendCodeServiceList; // ToDo отсортировать по приоритету либо отсортировать ниже по типу
    private SendCodeService sendErrorService;

    public SendByService(List<SendCodeService> sendCodeServiceList,
                         SendCodeService sendErrorService) {
        this.sendCodeServiceList = sendCodeServiceList;
        this.sendErrorService = sendErrorService;
    }

    public Codes send(Codes code, Car car) {
        if(!car.codes().contains(code.name())){
            throw new NumberFormatException("error codes");
        }
       var service = getPriorityService(car);
       try{
           return service.send(code);
       }catch (Exception e){
           var services = sendCodeServiceList.stream().filter(sendService->
               car.sendTypes().stream().anyMatch(sendService::supports)
           ).toList();
           for(SendCodeService srv : services) {
               try{
                   return srv.send(code);
               }catch (Exception eee){}
           }
       }
       return Codes.error;
    }

    private SendCodeService getPriorityService(Car car) {
        return sendCodeServiceList.stream()
                .filter(service->  service.supports(SendType.valueOf(car.prioritizedSendType())))
                .findFirst()
                .orElse(sendErrorService);

    }


}

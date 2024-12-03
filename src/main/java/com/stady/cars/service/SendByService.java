package com.stady.cars.service;
import com.stady.cars.domain.model.Car;
import com.stady.cars.domain.model.CommandTypeEnum;
import com.stady.cars.domain.model.SendType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SendByService  {
    private List<SendCodeService> sendCodeServiceList; // Fixed ToDo отсортировать по приоритету либо отсортировать ниже по типу
    private SendCodeService sendErrorService;

    public SendByService(List<SendCodeService> sendCodeServiceList,
                         SendCodeService sendErrorService) {
        this.sendCodeServiceList = sendCodeServiceList;
        Comparator<SendCodeService> comp = new Comparator<SendCodeService>() {
            @Override
            public int compare(SendCodeService s1, SendCodeService s2) {
                Optional<SendType> sendType1 = Arrays.stream(SendType.values()).filter(sendType-> s1.supports(sendType)).findAny();
                Optional<SendType> sendType2 = Arrays.stream(SendType.values()).filter(sendType-> s2.supports(sendType)).findAny();
                return sendType1.flatMap( st1 -> sendType2.map(st2-> st1.getValue().compareTo(st2.getValue()))).get();
            }
        };
        this.sendErrorService = sendErrorService;
    }

    public CommandTypeEnum send(CommandTypeEnum code, Car car) {
        if(!car.codes().contains(code)){
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
       return CommandTypeEnum.ERROR;
    }

    private SendCodeService getPriorityService(Car car) {
        return sendCodeServiceList.stream()
                .filter(service->  service.supports(car.prioritizedSendType()))
                .findFirst()
                .orElse(sendErrorService);

    }


}

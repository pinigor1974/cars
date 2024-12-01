package com.stady.cars.service;
import com.stady.cars.data.Car;
import com.stady.cars.data.Codes;
import com.stady.cars.data.SendType;

import java.util.List;

public class SendByService  {
    private List<SendCodeService> sendCodeServiceList;
    private SendCodeService sendErrorService;

    private SendCodeService getPriorityService(Car car) {
        return sendCodeServiceList.stream()
                .filter(service->  service.supports(SendType.valueOf(car.sendType())))
                .findFirst()
                .orElse(sendErrorService);

    }

    public SendByService(List<SendCodeService> sendCodeServiceList,
                         SendCodeService sendErrorService) {
        this.sendCodeServiceList = sendCodeServiceList;
        this.sendErrorService = sendErrorService;
    }


    public List<SendCodeService> getServiceList(){
        return sendCodeServiceList;
    }


    public Codes send(Codes code, Car car) {
        if(!car.codes().contains(code.name())){
            throw new NumberFormatException("error codes");
        }
       var service = getPriorityService(car);
       try{
           return service.send(code);
       }catch (Exception e){
           var services = sendCodeServiceList.stream().filter(sendService-> {
            return car.sendTypes().stream().anyMatch( st -> sendService.supports(SendType.valueOf(st)));
           } ).toList();
           for(SendCodeService srv : services) {
               try{
                   return srv.send(code);
               }catch (Exception eee){}
           }
       }
       return Codes.error;
    };
}
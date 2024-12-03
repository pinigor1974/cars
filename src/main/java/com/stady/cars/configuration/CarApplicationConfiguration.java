package com.stady.cars.configuration;


import com.stady.cars.domain.model.Car;
import com.stady.cars.domain.model.Cars;
import com.stady.cars.domain.model.Services;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

@ConfigurationProperties(prefix = "app")
public class CarApplicationConfiguration {
    private Map<String, String> codes;

    public void setCars(Map<Cars, Car> cars) {
        this.cars = cars;
    }

    private Map<Cars, Car> cars;
    private Map<Services, String> services;

    public Map<Cars, Car> getCars(){ return cars;}
    public Map<String, String> getCodes(){ return codes;}
    public Map<Services, String> getServices(){ return services;}

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    public void setServices(Map<Services, String> services) {
        this.services = services;
    }
}

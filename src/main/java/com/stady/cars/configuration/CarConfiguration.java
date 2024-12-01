package com.stady.cars.configuration;


import com.stady.cars.data.Car;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

import java.util.*;

@ConfigurationProperties(prefix = "app")
public class CarConfiguration {
    private Map<String, String> codes;

    public void setCars(Map<String, Car> cars) {
        this.cars = cars;
    }

    private Map<String, Car> cars;
    private Map<String, String> deliveryTypes;

    public Map<String, Car> getCars(){ return cars;}
    public Map<String, String> getCodes(){ return codes;}
    public Map<String, String> getDeliveryTypes(){ return deliveryTypes;}

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    public void setDeliveryTypes(Map<String, String> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }
}

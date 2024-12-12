package pro.nevercute.cars.configuration;


import pro.nevercute.cars.adapter.out.inMemory.domain.Car;
import pro.nevercute.cars.adapter.out.inMemory.domain.Cars;
import pro.nevercute.cars.adapter.out.inMemory.domain.Services;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

@ConfigurationProperties(prefix = "app")
public class CarApplicationProperties {
    private Map<String, String> codes;

    public void setCars(Map<Cars, Car> cars) {
        this.cars = cars;
    }

    private Map<Cars, Car> cars;
    private Map<Services, String> services;

    public Map<Cars, Car> getCars(){ return cars;}

    public Optional<Car> findCarByNameAndNumber(String name, String number){
        return cars.values().stream()
                .filter(it -> it.getNumber().equals(number) && it.getName().equals(name))
                .findFirst();
    }
    public Map<String, String> getCodes(){ return codes;}
    public Map<Services, String> getServices(){ return services;}

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    public void setServices(Map<Services, String> services) {
        this.services = services;
    }
}

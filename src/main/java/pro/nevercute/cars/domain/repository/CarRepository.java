package pro.nevercute.cars.domain.repository;

import pro.nevercute.cars.domain.model.Car;

import java.util.Optional;

public interface CarRepository {
    Optional<Car> findByModelNameAndNumber(String modelName, String modelNumber);
    Car save(Car car);
}

package com.stady.cars.domain.repository;

import com.stady.cars.domain.model.Car;

import java.util.Optional;

public interface CarRepository {
    Optional<Car> findByModelNameAndNumber(String modelName, String modelNumber);
}

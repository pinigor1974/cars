package com.stady.cars.adapter.out.inMemory;

import com.stady.cars.configuration.CarApplicationProperties;
import com.stady.cars.domain.model.Car;
import com.stady.cars.domain.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemoryCarRepositoryService implements CarRepository {

    private final CarApplicationProperties carApplicationProperties;

    @Override
    public Optional<Car> findByModelNameAndNumber(String modelName, String modelNumber) {
        return carApplicationProperties.findCarByNameAndNumber(modelName, modelNumber);
    }
}

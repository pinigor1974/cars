package pro.nevercute.cars.adapter.out.inMemory;

import pro.nevercute.cars.adapter.mapping.mapstruct.mapper.CarMapper;
import pro.nevercute.cars.configuration.CarApplicationProperties;
import pro.nevercute.cars.domain.model.Car;
import pro.nevercute.cars.domain.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemoryCarRepositoryService implements CarRepository {

    private final CarApplicationProperties carApplicationProperties;
    private final CarMapper carMapper;

    @Override
    public Optional<Car> findByModelNameAndNumber(String modelName, String modelNumber) {
        return carApplicationProperties.findCarByNameAndNumber(modelName, modelNumber)
                .map(carMapper::mapToDomain);
    }

    @Override
    public Car save(Car car) {
        return car;
    }
}

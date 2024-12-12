package pro.nevercute.cars.application.service;

import pro.nevercute.cars.domain.model.Car;
import pro.nevercute.cars.domain.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRepositoryService {

    private final CarRepository carRepository;

    public Car findByModelNameAndNumber(String modelName, String modelNumber) {
        return carRepository.findByModelNameAndNumber(modelName, modelNumber)
                .orElseThrow(() -> {
                    throw new RuntimeException(
                            "Не удалось найти настройки машины с номером %s".formatted(modelNumber)
                    );
                });
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }
}

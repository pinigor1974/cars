package pro.nevercute.cars.adapter.mapping.mapstruct.mapper;

import pro.nevercute.cars.adapter.mapping.mapstruct.MapstructConfig;
import pro.nevercute.cars.adapter.out.inMemory.domain.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        config = MapstructConfig.class,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public abstract class CarMapper {

    public abstract Car mapToAdapter(pro.nevercute.cars.domain.model.Car car);
    public abstract pro.nevercute.cars.domain.model.Car mapToDomain(Car car);
}

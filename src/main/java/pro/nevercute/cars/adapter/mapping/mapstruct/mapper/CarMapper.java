package pro.nevercute.cars.adapter.mapping.mapstruct.mapper;

import org.mapstruct.Mapping;
import pro.nevercute.cars.adapter.mapping.mapstruct.MapstructConfig;
import pro.nevercute.cars.adapter.out.inMemory.domain.Car;
import org.mapstruct.Mapper;
import pro.nevercute.cars.adapter.out.inMemory.domain.SendType;
import pro.nevercute.cars.domain.model.CommandTypeEnum;

@Mapper(
        config = MapstructConfig.class
)
public abstract class CarMapper {

    public abstract Car mapToAdapter(pro.nevercute.cars.domain.model.Car domain);

    public abstract pro.nevercute.cars.domain.model.Car mapToDomain(Car adapter);
    public abstract SendType mapToAdapter(pro.nevercute.cars.domain.model.SendType domain);
    public abstract pro.nevercute.cars.domain.model.SendType mapToDomain(SendType adapter);
    public abstract CommandTypeEnum mapToAdapter(pro.nevercute.cars.domain.model.CommandTypeEnum domain);
    public abstract pro.nevercute.cars.domain.model.CommandTypeEnum mapToDomain(CommandTypeEnum adapter);

}

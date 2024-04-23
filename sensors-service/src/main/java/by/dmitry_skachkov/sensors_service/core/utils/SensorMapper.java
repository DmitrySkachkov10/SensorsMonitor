package by.dmitry_skachkov.sensors_service.core.utils;

import by.dmitry_skachkov.sensors_service.core.dto.SensorDTO;
import by.dmitry_skachkov.sensors_service.repo.entity.SensorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface SensorMapper {
    SensorMapper INSTANCE = Mappers.getMapper(SensorMapper.class);


    @Mapping(source = "dtCreate", target = "dtCreate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(source = "dtUpdate", target = "dtUpdate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    SensorDTO toDto(SensorEntity entity);

    SensorEntity toEntity(SensorDTO dto);
}
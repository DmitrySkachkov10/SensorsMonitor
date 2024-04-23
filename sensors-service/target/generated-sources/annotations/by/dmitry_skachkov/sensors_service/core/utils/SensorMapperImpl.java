package by.dmitry_skachkov.sensors_service.core.utils;

import by.dmitry_skachkov.sensors_service.core.dto.SensorDTO;
import by.dmitry_skachkov.sensors_service.repo.entity.SensorEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-23T15:28:23+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class SensorMapperImpl implements SensorMapper {

    @Override
    public SensorDTO toDto(SensorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SensorDTO sensorDTO = new SensorDTO();

        sensorDTO.setDtCreate( entity.getDtCreate() );
        sensorDTO.setDtUpdate( entity.getDtUpdate() );
        sensorDTO.setUuid( entity.getUuid() );
        sensorDTO.setName( entity.getName() );
        sensorDTO.setModel( entity.getModel() );
        sensorDTO.setRangeFrom( entity.getRangeFrom() );
        sensorDTO.setRangeTo( entity.getRangeTo() );
        sensorDTO.setLocation( entity.getLocation() );
        sensorDTO.setDescription( entity.getDescription() );

        return sensorDTO;
    }

    @Override
    public SensorEntity toEntity(SensorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SensorEntity sensorEntity = new SensorEntity();

        sensorEntity.setUuid( dto.getUuid() );
        sensorEntity.setName( dto.getName() );
        sensorEntity.setModel( dto.getModel() );
        sensorEntity.setRangeFrom( dto.getRangeFrom() );
        sensorEntity.setRangeTo( dto.getRangeTo() );
        sensorEntity.setLocation( dto.getLocation() );
        sensorEntity.setDtCreate( dto.getDtCreate() );
        sensorEntity.setDtUpdate( dto.getDtUpdate() );
        sensorEntity.setDescription( dto.getDescription() );

        return sensorEntity;
    }
}

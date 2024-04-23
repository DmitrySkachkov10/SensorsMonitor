package by.dmitry_skachkov.sensors_service.service.api;

import by.dmitry_skachkov.sensors_service.core.dto.SensorTypeDto;
import by.dmitry_skachkov.sensors_service.repo.entity.SensorTypeEntity;

import java.util.List;

public interface ISensorTypeService {

    List<SensorTypeDto> load();

}

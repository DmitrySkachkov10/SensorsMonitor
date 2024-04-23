package by.dmitry_skachkov.sensors_service.service;

import by.dmitry_skachkov.sensors_service.core.dto.SensorTypeDto;
import by.dmitry_skachkov.sensors_service.repo.api.ISensorTypeRepo;
import by.dmitry_skachkov.sensors_service.service.api.ISensorTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorTypeService implements ISensorTypeService {

    private final ISensorTypeRepo sensorTypeRepo;

    public SensorTypeService(ISensorTypeRepo sensorTypeRepo) {
        this.sensorTypeRepo = sensorTypeRepo;
    }

    @Override
    public List<SensorTypeDto> load() {
        return sensorTypeRepo.findAll()
                .stream()
                .map(m -> new SensorTypeDto(
                                m.getId(),
                                m.getType(),
                                m.getUnit())
                ).toList();
    }
}

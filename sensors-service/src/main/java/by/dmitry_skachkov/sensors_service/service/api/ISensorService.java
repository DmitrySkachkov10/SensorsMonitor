package by.dmitry_skachkov.sensors_service.service.api;

import by.dmitry_skachkov.sensors_service.core.dto.SensorDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;


public interface ISensorService {

    List<SensorDTO> getSensors(Pageable pageable);

    SensorDTO getById(UUID uuid);

    void addSensor(SensorDTO sensorDTO);

    void updateSensor(SensorDTO sensorDTO, UUID uuid, long localDateTime);

    void deleteSensor(UUID uuid);
}

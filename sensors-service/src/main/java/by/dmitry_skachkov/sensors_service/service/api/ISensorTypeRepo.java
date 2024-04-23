package by.dmitry_skachkov.sensors_service.service.api;

import by.dmitry_skachkov.sensors_service.repo.entity.SensorTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISensorTypeRepo extends JpaRepository<SensorTypeEntity, Integer> {
    SensorTypeEntity getByTypeAndUnit(String type, String unit);
}

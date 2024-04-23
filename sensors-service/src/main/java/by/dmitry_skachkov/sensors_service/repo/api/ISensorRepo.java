package by.dmitry_skachkov.sensors_service.repo.api;

import by.dmitry_skachkov.sensors_service.repo.entity.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface ISensorRepo extends JpaRepository<SensorEntity, UUID> {

    List<SensorEntity> getAllBy(Pageable pageable);
}

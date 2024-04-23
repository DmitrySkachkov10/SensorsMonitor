package by.dmitry_skachkov.sensors_service.service;

import by.dmitry_skachkov.sensors_service.core.dto.SensorDTO;
import by.dmitry_skachkov.sensors_service.core.utils.SensorMapper;
import by.dmitry_skachkov.sensors_service.repo.api.ISensorRepo;
import by.dmitry_skachkov.sensors_service.repo.entity.SensorEntity;
import by.dmitry_skachkov.sensors_service.repo.entity.SensorTypeEntity;
import by.dmitry_skachkov.sensors_service.service.api.ISensorService;
import by.dmitry_skachkov.sensors_service.repo.api.ISensorTypeRepo;
import by.dmitryskachkov.exception.exceptions.ValidationException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class SensorService implements ISensorService {

    private final ISensorRepo sensorRepo;

    private final ISensorTypeRepo sensorTypeRepo;


    public SensorService(ISensorRepo sensorRepo, ISensorTypeRepo sensorTypeRepo) {
        this.sensorRepo = sensorRepo;
        this.sensorTypeRepo = sensorTypeRepo;
    }


    @Override
    public List<SensorDTO> getSensors(Pageable pageable) {

        return sensorRepo.getAllBy(pageable)
                .stream()
                .map(m -> {
                    SensorDTO dto = SensorMapper.INSTANCE.toDto(m);
                    dto.setType(m.getSensorType().getType());
                    dto.setUnit(m.getSensorType().getUnit());
                    return dto;
                })
                .toList();
    }

    @Override
    public SensorDTO getById(UUID uuid) {
        return SensorMapper.INSTANCE.toDto(sensorRepo.getById(uuid));
    }

    @Override
    @Transactional
    public void addSensor(SensorDTO sensorDTO) {
        if (isValid(sensorDTO)) {

            SensorTypeEntity sensorTypeEntity = getTypeEntity(sensorDTO.getType(), sensorDTO.getUnit());
            SensorEntity sensorEntity = SensorMapper.INSTANCE.toEntity(sensorDTO);

            sensorEntity.setUuid(UUID.randomUUID());
            sensorEntity.setDtCreate(LocalDateTime.now());
            sensorEntity.setDtUpdate(sensorEntity.getDtCreate());
            sensorEntity.setSensorType(sensorTypeEntity);

            sensorRepo.save(sensorEntity);
        }
    }

    @Override
    @Transactional
    public void updateSensor(SensorDTO sensorDTO, UUID uuid, long updateTime) {

        SensorEntity sensorEntity = sensorRepo.getById(uuid);

        LocalDateTime latestUpdateTime = sensorEntity.getDtUpdate().truncatedTo(ChronoUnit.MILLIS);
        LocalDateTime lastUpdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(updateTime), ZoneOffset.UTC);

        if (!latestUpdateTime.equals(lastUpdateTime)) {
            throw new ValidationException("Запрос некорректен. Сервер не может обработать запрос");
        }

        sensorEntity.setName(sensorDTO.getName());
        sensorEntity.setModel(sensorDTO.getModel());
        sensorEntity.setRangeFrom(sensorDTO.getRangeFrom());
        sensorEntity.setRangeTo(sensorDTO.getRangeTo());
        sensorEntity.setDescription(sensorDTO.getDescription());

        sensorEntity.setDtUpdate(LocalDateTime.now());
        sensorEntity.setSensorType(getTypeEntity(sensorDTO.getType(), sensorDTO.getUnit()));

        sensorRepo.save(sensorEntity);

    }

    @Override
    @Transactional
    public void deleteSensor(UUID uuid) {
        sensorRepo.deleteById(uuid);
    }

    private boolean isValid(SensorDTO sensorDTO) {
        return sensorDTO.getDescription().length() <= 200 && sensorDTO.getLocation().length() <= 40 &&
                sensorDTO.getRangeFrom() < sensorDTO.getRangeTo() && !sensorDTO.getModel().isEmpty() &&
                !sensorDTO.getName().isEmpty() && sensorDTO.getModel().length() <= 15 && sensorDTO.getName().length() <= 30;
    }

    private SensorTypeEntity getTypeEntity(String type, String unit) {
        return sensorTypeRepo.getByTypeAndUnit(type, unit);
    }
}

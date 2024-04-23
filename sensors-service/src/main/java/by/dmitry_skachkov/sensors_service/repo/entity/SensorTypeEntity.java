package by.dmitry_skachkov.sensors_service.repo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "sensor_type", schema = "sensors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorTypeEntity {

    @Id
    private int id;

    private String type;

    private String unit;

    @OneToMany(mappedBy = "sensorType")
    private List<SensorEntity> sensors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorTypeEntity that = (SensorTypeEntity) o;
        return id == that.id && Objects.equals(type, that.type) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, unit);
    }
}

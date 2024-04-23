package by.dmitry_skachkov.sensors_service.repo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "sensor", schema = "sensors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorEntity {

    @Id
    private UUID uuid;
    private String name;

    private String model;

    @Column(name = "range_from")
    private int rangeFrom;

    @Column(name = "range_to")
    private int rangeTo;

    @Column(name = "type_id", insertable = false, updatable = false)
    private int typeId;

    private String location;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Column(name = "dt_update")
    @Version
    private LocalDateTime dtUpdate;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private SensorTypeEntity sensorType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorEntity that = (SensorEntity) o;
        return rangeFrom == that.rangeFrom && rangeTo == that.rangeTo && Objects.equals(name, that.name) && Objects.equals(model, that.model) && Objects.equals(location, that.location) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, rangeFrom, rangeTo, location, dtCreate, dtUpdate, description);
    }
}

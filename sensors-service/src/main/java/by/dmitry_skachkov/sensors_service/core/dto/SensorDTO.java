package by.dmitry_skachkov.sensors_service.core.dto;

import by.dmitry_skachkov.sensors_service.core.utils.LocalDateTimeUnixTimestampSerializer;
import by.dmitry_skachkov.sensors_service.core.utils.UnixTimestampToLocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDTO {

    private UUID uuid;

    private String name;

    private String model;

    private int rangeFrom;

    private int rangeTo;

    private String location;

    @JsonSerialize(using = LocalDateTimeUnixTimestampSerializer.class)
    @JsonDeserialize(using = UnixTimestampToLocalDateTimeDeserializer.class)
    private LocalDateTime dtCreate;

    @JsonSerialize(using = LocalDateTimeUnixTimestampSerializer.class)
    @JsonDeserialize(using = UnixTimestampToLocalDateTimeDeserializer.class)
    private LocalDateTime dtUpdate;

    private String description;

    private String unit;

    private String type;

}

package by.dmitry_skachkov.sensors_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorTypeDto {
    private int id;

    private String type;

    private String unit;
}

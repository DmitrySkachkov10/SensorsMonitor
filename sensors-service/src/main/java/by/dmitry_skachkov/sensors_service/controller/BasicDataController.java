package by.dmitry_skachkov.sensors_service.controller;

import by.dmitry_skachkov.sensors_service.core.dto.SensorTypeDto;
import by.dmitry_skachkov.sensors_service.service.api.ISensorTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basic/data")
public class BasicDataController {

    private final ISensorTypeService sensorTypeService;

    public BasicDataController(ISensorTypeService sensorTypeService) {
        this.sensorTypeService = sensorTypeService;
    }

    @GetMapping("/load")
    public ResponseEntity<List<SensorTypeDto>> load() {
        return new ResponseEntity<>(sensorTypeService.load(), HttpStatus.OK);
    }
}

package by.dmitry_skachkov.sensors_service.controller;

import by.dmitry_skachkov.sensors_service.core.dto.SensorDTO;
import by.dmitry_skachkov.sensors_service.service.api.ISensorService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    public final ISensorService service;

    public final static int SIZE = 4;

    public SensorController(ISensorService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<SensorDTO>> getSensors(@RequestParam(required = false, defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, SIZE);
        return new ResponseEntity<>(service.getSensors(pageable), HttpStatus.OK);
    }
//
//    @GetMapping
//    public ResponseEntity<SensorDTO> getSensors(@RequestParam UUID uuid) {
//        return new ResponseEntity<>(service.getById(uuid), HttpStatus.OK);
//    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<String> addSensor(@RequestBody SensorDTO sensorDTO) {
        service.addSensor(sensorDTO);
        return new ResponseEntity<>("Сенсор добавлен", HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}/update/{dt_update}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<String> updateSensor(@PathVariable UUID uuid,
                                               @PathVariable long dt_update,
                                               @RequestBody SensorDTO sensorDTO) {
        service.updateSensor(sensorDTO, uuid, dt_update);
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}/delete")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<String> delete(@PathVariable UUID uuid){
        service.deleteSensor(uuid);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}

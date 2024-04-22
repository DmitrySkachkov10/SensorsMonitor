package by.dmitry_skachkov.user_service.controller;


import by.dmitryskachkov.dto.ExcepionResponseDto;
import by.dmitryskachkov.exception.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExcepionResponseDto> defaultErrorHandler(ValidationException e) {
        return new ResponseEntity<>(new ExcepionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
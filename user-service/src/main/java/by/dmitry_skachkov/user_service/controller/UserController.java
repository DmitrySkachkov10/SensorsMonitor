package by.dmitry_skachkov.user_service.controller;

import by.dmitry_skachkov.user_service.core.dto.UserLoginDTO;
import by.dmitry_skachkov.user_service.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO loginDTO) {
        return new ResponseEntity<>(service.logIn(loginDTO), HttpStatus.OK);
    }

//    @PostMapping("/registration")
//    public ResponseEntity<String> registrate(@RequestBody UserLoginDTO loginDTO) {
//        service.register(loginDTO);
//        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);
//    }

}

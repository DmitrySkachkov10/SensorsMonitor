package by.dmitry_skachkov.userse_rvice.service;

import by.dmitry_skachkov.userse_rvice.core.dto.UserLoginDTO;
import by.dmitry_skachkov.userse_rvice.core.enums.Role;
import by.dmitry_skachkov.userse_rvice.core.utils.JwtTokenHandler;
import by.dmitry_skachkov.userse_rvice.core.utils.UserSecurity;
import by.dmitry_skachkov.userse_rvice.repo.api.IUserRepo;
import by.dmitry_skachkov.userse_rvice.repo.entity.UserEntity;
import by.dmitry_skachkov.userse_rvice.service.api.IUserService;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService implements IUserService {

    private final IUserRepo userRepo;

    private final JwtTokenHandler jwtTokenHandler;

    public UserService(IUserRepo userRepo, JwtTokenHandler jwtTokenHandler) {
        this.userRepo = userRepo;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Override
    @Transactional
    public void register(UserLoginDTO userLogin) {
        userRepo.save(new UserEntity(UUID.randomUUID(),
                userLogin.getLogin(),
                BCrypt.hashpw(userLogin.getPassword(), BCrypt.gensalt()),
                Role.VIEWER));
    }

    @Override //todo свою ошибку и нормально описать
    public String logIn(UserLoginDTO userLogin) {
        UserEntity userEntity = userRepo.findByLoginAndPassword(userLogin.getLogin(), userLogin.getPassword());
        if (userEntity == null){
            throw new RuntimeException("Запрос некорректен. Сервер не может обработать запрос");
        }

        if (!BCrypt.checkpw(userLogin.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Запрос некорректен. Сервер не может обработать запрос");
        }

        return jwtTokenHandler.generateAccessToken(new UserSecurity(
                userEntity.getUuid().toString(),
                userEntity.getRole().toString()
        ));
    }
}

//todo метод для проверки валидации при регистрации
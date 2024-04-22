package by.dmitry_skachkov.user_service.service;

import by.dmitry_skachkov.user_service.core.dto.UserLoginDTO;
import by.dmitry_skachkov.user_service.core.exceptions.ValidationException;
import by.dmitry_skachkov.user_service.core.utils.JwtTokenHandler;
import by.dmitry_skachkov.user_service.core.utils.UserSecurity;
import by.dmitry_skachkov.user_service.repo.api.IUserRepo;
import by.dmitry_skachkov.user_service.repo.entity.UserEntity;
import by.dmitry_skachkov.user_service.service.api.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    private static final String INVALID_REQUEST_MESSAGE = "Запрос некорректен. Сервер не может обработать запрос";

    private final IUserRepo userRepo;

    private final JwtTokenHandler jwtTokenHandler;


    public UserService(IUserRepo userRepo, JwtTokenHandler jwtTokenHandler) {
        this.userRepo = userRepo;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Override
    public String logIn(UserLoginDTO userLogin) {
        UserEntity userEntity = userRepo.findByLogin(userLogin.getLogin());

        if (userEntity == null) {
            throw new ValidationException(INVALID_REQUEST_MESSAGE);
        }

        if (!BCrypt.checkpw(userLogin.getPassword(), userEntity.getPassword())) {
            throw new ValidationException(INVALID_REQUEST_MESSAGE);
        }

        return jwtTokenHandler.generateAccessToken(new UserSecurity(
                userEntity.getUuid().toString(),
                userEntity.getRole().toString()
        ));
    }


//    @Override
//    @Transactional
//    public void register(UserLoginDTO userLogin) {
//        userRepo.save(new UserEntity(UUID.randomUUID(),
//                userLogin.getLogin(),
//                BCrypt.hashpw(userLogin.getPassword(), BCrypt.gensalt()),
//                Role.VIEWER));
//    }
}


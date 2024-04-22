package by.dmitry_skachkov.user_service.service.api;

import by.dmitry_skachkov.user_service.core.dto.UserLoginDTO;

public interface IUserService {

//    void register(UserLoginDTO userLoginDTO);

    String logIn(UserLoginDTO userLoginDTO);
}

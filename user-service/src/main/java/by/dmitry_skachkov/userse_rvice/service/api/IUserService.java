package by.dmitry_skachkov.userse_rvice.service.api;

import by.dmitry_skachkov.userse_rvice.core.dto.UserLoginDTO;

public interface IUserService {

    void register(UserLoginDTO userLoginDTO);

    String logIn(UserLoginDTO userLoginDTO);
}

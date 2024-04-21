package by.dmitry_skachkov.userse_rvice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDTO {

    private String login;

    private String password;

}

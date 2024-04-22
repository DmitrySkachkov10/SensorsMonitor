package by.dmitry_skachkov.userse_rvice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDTO {

    private String login;

    private String password;

}

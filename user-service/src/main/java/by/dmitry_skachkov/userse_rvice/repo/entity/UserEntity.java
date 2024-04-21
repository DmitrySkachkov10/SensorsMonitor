package by.dmitry_skachkov.userse_rvice.repo.entity;

import by.dmitry_skachkov.userse_rvice.core.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Entity
@Table(name = "user", schema = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    private UUID uuid;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}

package by.dmitry_skachkov.userse_rvice.repo.entity;

import by.dmitry_skachkov.userse_rvice.core.enums.Role;
import jakarta.persistence.*;
import lombok.*;


import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    private UUID uuid;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, login, password, role);
    }
}

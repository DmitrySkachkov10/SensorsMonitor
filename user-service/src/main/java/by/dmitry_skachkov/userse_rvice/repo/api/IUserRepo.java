package by.dmitry_skachkov.userse_rvice.repo.api;

import by.dmitry_skachkov.userse_rvice.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepo extends JpaRepository<UserEntity, UUID> {

    UserEntity findByLoginAndPassword(String login, String password);
}

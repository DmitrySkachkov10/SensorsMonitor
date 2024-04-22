package by.dmitry_skachkov.user_service.repo.api;

import by.dmitry_skachkov.user_service.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepo extends JpaRepository<UserEntity, UUID> {

    UserEntity findByLogin(String login);
}

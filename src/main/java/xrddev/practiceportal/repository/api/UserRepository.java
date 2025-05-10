package xrddev.practiceportal.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
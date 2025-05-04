package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // Για μελλοντική επικύρωση email
    Optional<User> findByUsername(String username); // Για μελλοντική επικύρωση username
    boolean existsByUsername(String username);

}
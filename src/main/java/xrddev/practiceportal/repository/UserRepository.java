package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
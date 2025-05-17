package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.user.Professor;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail(String email);
}
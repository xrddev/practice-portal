package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
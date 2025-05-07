package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.Professor;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByEmail(String email);

    @EntityGraph(attributePaths = {"supervisedPositions", "supervisedPositions.student"})
    Optional<Professor> findWithPositionsByEmail(String email);
}
package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.internship_evaluations.StudentInternshipEvaluation;

public interface StudentInternshipEvaluationRepository extends JpaRepository<StudentInternshipEvaluation, Long> {
}

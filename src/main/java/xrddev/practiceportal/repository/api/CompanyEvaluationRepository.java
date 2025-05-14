package xrddev.practiceportal.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.internship.CompanyEvaluation;

public interface CompanyEvaluationRepository extends JpaRepository<CompanyEvaluation, Long> {
}

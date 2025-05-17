package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xrddev.practiceportal.model.period.EvaluationPeriod;

@Repository
public interface EvaluationPeriodRepository extends JpaRepository<EvaluationPeriod, Integer> {
}
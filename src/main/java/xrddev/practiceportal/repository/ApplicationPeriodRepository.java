package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xrddev.practiceportal.model.period.ApplicationPeriod;

@Repository
public interface ApplicationPeriodRepository extends JpaRepository<ApplicationPeriod, Integer> {
}
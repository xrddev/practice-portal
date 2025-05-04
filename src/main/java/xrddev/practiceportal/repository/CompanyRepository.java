package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
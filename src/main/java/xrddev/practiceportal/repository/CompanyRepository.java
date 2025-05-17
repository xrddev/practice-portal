package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.user.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmail(String email);
}
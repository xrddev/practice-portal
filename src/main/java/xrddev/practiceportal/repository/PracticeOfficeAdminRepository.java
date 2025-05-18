package xrddev.practiceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.practice_office.PracticeOfficeAdmin;

import java.util.Optional;

public interface PracticeOfficeAdminRepository extends JpaRepository<PracticeOfficeAdmin, Long> {
    Optional<PracticeOfficeAdmin> findByEmail(String email);
}
package xrddev.practiceportal.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import xrddev.practiceportal.model.user.PracticeOfficeAdmin;

import java.util.Optional;

public interface PracticeOfficeAdminRepository extends JpaRepository<PracticeOfficeAdmin, Long> {
    Optional<PracticeOfficeAdmin> findByEmail(String email);
}